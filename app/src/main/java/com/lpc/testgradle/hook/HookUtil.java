package com.lpc.testgradle.hook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by zhoukaitong on 2021/10/29.
 * Description:
 */
public class HookUtil implements IHook {

    private final Context mContext;

    public HookUtil(Context context) {
        this.mContext = context.getApplicationContext();
    }


    /***********************************************************************
     *                            hook AMS
     **********************************************************************/

    /**
     * hookAMS，骗过AMS的校验
     */
    @Override
    public void hookAMS() {

        try {
            final Class<?> ActivityManagerClz;
            final String getServiceMethodStr;
            final String IActivityManagerSingletonFieldStr;

            if (ifSdkOverIncluding29()) {//29的ams获取方式是通过ActivityTaskManager.getService()
                ActivityManagerClz = Class.forName("android.app.ActivityTaskManager");
                getServiceMethodStr = "getService";
                IActivityManagerSingletonFieldStr = "IActivityTaskManagerSingleton";
            } else if (ifSdkOverIncluding26()) {//26，27，28的ams获取方式是通过ActivityManager.getService()
                ActivityManagerClz = Class.forName("android.app.ActivityManager");
                getServiceMethodStr = "getService";
                IActivityManagerSingletonFieldStr = "IActivityManagerSingleton";
            } else {//25往下，是ActivityManagerNative.getDefault()
                ActivityManagerClz = Class.forName("android.app.ActivityManagerNative");
                getServiceMethodStr = "getDefault";
                IActivityManagerSingletonFieldStr = "gDefault";
            }

            //最终目的是把 IActivityTaskManagerSingleton 里面的 mInstance 替换成自己的动态代理对象
            //1、调用ActivityTaskManager#getService()获取真实对象
            //2、基于IActivityTaskManager接口创建动态代理对象，代理第一步
            //3、把动态代理对象赋值给IActivityTaskManagerSingleton#mInstance
            // 经过以上三步，AMS本地对象就被我们hook掉了

            //获取AMS的本地代理对象
            Object ActivityManagerObj = ReflectUtil.invokeStaticMethod(ActivityManagerClz, getServiceMethodStr);

            Class<?> IActivityManagerClz;
            if (ifSdkOverIncluding29()) {
                IActivityManagerClz = Class.forName("android.app.IActivityTaskManager");
            } else {
                IActivityManagerClz = Class.forName("android.app.IActivityManager");
            }

            // 构建代理类需要两个东西用于创建伪装的Intent
            String packageName = Util.getPMName(mContext);
            String clz = Util.getHostClzName(mContext, packageName);
            //创建本地AMS的动态代理
            Object proxyIActivityManager =
                    Proxy.newProxyInstance(
                            Thread.currentThread().getContextClassLoader(),
                            new Class[]{IActivityManagerClz},
                            new AMSProxyInvocation(ActivityManagerObj, packageName, clz));


            //获取ActivityTaskManager中的Singleton，其中的mInstance字段就是这个AMS代理对象
            Object IActivityManagerSingleton = ReflectUtil.staticFieldValue(ActivityManagerClz, IActivityManagerSingletonFieldStr);

            //3.拿到AMS实例，然后用代理的AMS换掉真正的AMS，代理的AMS则是用 假的Intent骗过了 activity manifest检测.
            //偷梁换柱，把singleton的mInstance替换成我们的代理对象
            Field mInstanceField = ReflectUtil.findSingletonField("mInstance");
            mInstanceField.set(IActivityManagerSingleton, proxyIActivityManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String ORI_INTENT_TAG = "origin_intent";

    /**
     * 把InvocationHandler的实现类提取出来，因为这里包含了核心技术逻辑，最好独立，方便维护
     */
    private static class AMSProxyInvocation implements InvocationHandler {

        Object amObj;
        String packageName;//这两个String是用来构建Intent的ComponentName的
        String clz;

        public AMSProxyInvocation(Object amObj, String packageName, String clz) {
            this.amObj = amObj;
            this.packageName = packageName;
            this.clz = clz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.e("GlobalActivityHook", "method.getName() = " + method.getName());
            //proxy是创建出来的代理类，method是接口中的方法，args是接口执行时的实参
            if (method.getName().equals("startActivity")) {
                Log.d("GlobalActivityHook", "全局hook 到了 startActivity");

                Intent currentRealIntent = null;//侦测到startActivity动作之后，把intent存到这里
                int intentIndex = -1;
                //遍历参数，找到Intent
                for (int i = 0; i < args.length; i++) {
                    Object temp = args[i];
                    if (temp instanceof Intent) {
                        currentRealIntent = (Intent) temp;//这是原始的Intent,存起来,后面用得着
                        intentIndex = i;
                        break;
                    }
                }

                //构造自己的Intent，这是为了绕过manifest检测
                Intent proxyIntent = new Intent();
                ComponentName componentName = new ComponentName(packageName, clz);//用ComponentName重新创建一个intent
                proxyIntent.setComponent(componentName);
                proxyIntent.putExtra(ORI_INTENT_TAG, currentRealIntent);//将真正的proxy作为参数，存放到extras中，后面会拿出来还原

                args[intentIndex] = proxyIntent;//替换掉intent
                //哟，已经成功绕过了manifest清单检测. 那么，我不能老让它跳到 伪装的Activity啊，我要给他还原回去，那么，去哪里还原呢？
                //继续看源码。

            }
            return method.invoke(amObj, args);
        }
    }


    /***********************************************************************
     *                            hook mH
     **********************************************************************/

    //下面进行ActivityThread的mH的hook
    //将真实的Intent还原回去，让系统可以跳到原本该跳的地方
    @Override
    public void hookActivityThread_mH() {

        try {
            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");

            Object sCurrentActivityThread = ReflectUtil.staticFieldValue(activityThreadClazz, "sCurrentActivityThread");

            Handler mH = (Handler) ReflectUtil.fieldValue(sCurrentActivityThread, "mH");

            Field mCallBackField = ReflectUtil.findField(Handler.class, "mCallback");

            Handler.Callback callback;
            if (ifSdkOverIncluding28()) {
                //2.现在，造一个代理
                // 他就是一个简单的Handler子类
                callback = new ProxyHandlerCallback();//不需要重写全部mH，只需要对mH的callback进行重新定义
            } else {
                callback = new ActivityThreadHandlerCallBack(mContext);
            }

            //3.替换
            //将Handler的mCallback成员，替换成创建出来的代理HandlerCallback
            mCallBackField.set(mH, callback);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意，这里有个坑
     * android.os.handler 这个类有 3个 callback，按照优先级，依次是 msg的callback，自己成员变量mCallback，自己的成员方法 handleMessage()
     * 其中，msg.callback一般很少用，但是它是最优先的，如果有一个Message.存在callback非空成员,那么它是先执行，后面两个就没戏了。
     * 如果 handler自己的成员变量mCallback，非空，那么 handlerMessage()方法就没戏了
     * 前面两个都执行，那么handlerMessage才会执行，
     * 这个叫责任链模式？根据实际条件决定代码分支。
     */
    private static class ProxyHandlerCallback implements Handler.Callback {

        private int EXECUTE_TRANSACTION = 159;//这个值，是android.app.ActivityThread的内部类H 中定义的常量EXECUTE_TRANSACTION

        @Override
        public boolean handleMessage(Message msg) {
            boolean result = false;//返回值，请看Handler的源码，dispatchMessage就会懂了
            //Handler的dispatchMessage有3个callback优先级，首先是msg自带的callback，其次是Handler的成员mCallback,最后才是Handler类自身的handlerMessage方法,
            //它成员mCallback.handleMessage的返回值为true，则不会继续往下执行 Handler.handlerMessage
            //我们这里只是要hook，插入逻辑，所以必须返回false，让Handler原本的handlerMessage能够执行.
            if (msg.what == EXECUTE_TRANSACTION) {//这是跳转的时候,要对intent进行还原
                try {
                    //先把相关@hide的类都建好
                    Class<?> ClientTransactionClz = Class.forName("android.app.servertransaction.ClientTransaction");
                    Class<?> LaunchActivityItemClz = Class.forName("android.app.servertransaction.LaunchActivityItem");

                    //AMS校验完之后，在mMainThread.sendActivityResult中会调用ClientTransaction#addCallback
                    Field mActivityCallbacksField = ClientTransactionClz.getDeclaredField("mActivityCallbacks");//ClientTransaction的成员
                    mActivityCallbacksField.setAccessible(true);
                    //类型判定，好习惯
                    if (!ClientTransactionClz.isInstance(msg.obj)) return true;
                    Object mActivityCallbacksObj = mActivityCallbacksField.get(msg.obj);//根据源码，在这个分支里面,msg.obj就是 ClientTransaction类型,所以，直接用
                    //拿到了ClientTransaction的List<ClientTransactionItem> mActivityCallbacks;
                    List list = (List) mActivityCallbacksObj;

                    if (list.size() == 0) return false;
                    Object LaunchActivityItemObj = list.get(0);//所以这里直接就拿到第一个就好了

                    //但是，最后从mH的witch case EXECUTE_TRANSACTION分支，去debug（因为无法继续往下查源码）的时候，发现原本塞进去的ActivityResultItem的list，居然变成了LaunchActivityItem的list，查了半天，也没查到是在源码何处发生的变化
                    if (!LaunchActivityItemClz.isInstance(LaunchActivityItemObj)) return true;
                    //这里必须判定 LaunchActivityItemClz，
                    // 因为 最初的ActivityResultItem传进去之后都被转化成了这LaunchActivityItemClz的实例

                    Field mIntentField = LaunchActivityItemClz.getDeclaredField("mIntent");
                    mIntentField.setAccessible(true);
                    Intent mIntent = (Intent) mIntentField.get(LaunchActivityItemObj);

                    Bundle extras = mIntent.getExtras();
                    if (extras != null) {
                        Intent oriIntent = (Intent) extras.get(ORI_INTENT_TAG);
                        //那么现在有了最原始的intent，应该怎么处理呢？
                        Log.d("1", "2");
                        mIntentField.set(LaunchActivityItemObj, oriIntent);
                    }

                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    public static class ActivityThreadHandlerCallBack implements Handler.Callback {

        private final Context mContext;

        public ActivityThreadHandlerCallBack(Context context) {
            mContext = context;
        }

        @Override
        public boolean handleMessage(Message msg) {
            int LAUNCH_ACTIVITY = 0;
            try {
                Class<?> clazz = Class.forName("android.app.ActivityThread$H");
                LAUNCH_ACTIVITY = (int) ReflectUtil.staticFieldValue(clazz, "LAUNCH_ACTIVITY");
            } catch (Exception e) {
            }
            if (msg.what == LAUNCH_ACTIVITY) {
                handleLaunchActivity(mContext, msg);
            }
            return false;
        }
    }

    private static void handleLaunchActivity(Context context, Message msg) {
        try {
            Object obj = msg.obj;

            Intent proxyIntent = (Intent) ReflectUtil.fieldValue(obj, "intent");
            //拿到之前真实要被启动的Intent 然后把Intent换掉
            Intent originallyIntent = proxyIntent.getParcelableExtra(ORI_INTENT_TAG);
            if (originallyIntent == null) {
                return;
            }
            proxyIntent.setComponent(originallyIntent.getComponent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***********************************************************************
     *                            hook PMS
     **********************************************************************/

    /**
     * 由于我只在SDK 28 对应的9.0设备上做过成功的试验，所以此方法命名为hookPMAfter28
     */
    @Override
    public void hookPMAfter28() {
        try {
            String pmName = Util.getPMName(mContext);
            String hostClzName = Util.getHostClzName(mContext, pmName);

            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
            Object sCurrentActivityThread = ReflectUtil.staticFieldValue(activityThreadClazz, "sCurrentActivityThread");//PM居然是来自ActivityThread

            Object iPackageManager = ReflectUtil.invokeMethod(sCurrentActivityThread, "getPackageManager");

            String packageName = Util.getPMName(mContext);
            PMSInvocationHandler handler = new PMSInvocationHandler(iPackageManager, packageName, hostClzName);
            Class<?> iPackageManagerIntercept = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new
                    Class<?>[]{iPackageManagerIntercept}, handler);
            // 获取 sPackageManager 属性
            Field sPackageManagerField = ReflectUtil.findField(sCurrentActivityThread, "sPackageManager");
            sPackageManagerField.set(sCurrentActivityThread, proxy);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    static class PMSInvocationHandler implements InvocationHandler {

        private Object base;
        private String packageName;
        private String hostClzName;

        public PMSInvocationHandler(Object base, String packageName, String hostClzName) {
            this.packageName = packageName;
            this.base = base;
            this.hostClzName = hostClzName;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("getActivityInfo")) {
                ComponentName componentName = new ComponentName(packageName, hostClzName);
                return method.invoke(base, componentName, PackageManager.GET_META_DATA, 0); //破费，一定是这样
            }

            return method.invoke(base, args);
        }
    }

    //设备系统版本是不是大于等于29(Android 10)
    private static boolean ifSdkOverIncluding29() {
        int SDK_INT = Build.VERSION.SDK_INT;
        return SDK_INT >= 29;
    }

    //设备系统版本是不是大于等于26(Android 8.0 Oreo)
    private static boolean ifSdkOverIncluding26() {
        int SDK_INT = Build.VERSION.SDK_INT;
        return SDK_INT >= 26;
    }

    //设备系统版本是不是大于等于28(Android 9.0 Pie)
    private static boolean ifSdkOverIncluding28() {
        int SDK_INT = Build.VERSION.SDK_INT;
        return SDK_INT >= 28;
    }

}
