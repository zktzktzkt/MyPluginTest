package com.asm.demo.flutter;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.idlefish.flutterboost.EventListener;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostDelegate;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.idlefish.flutterboost.ListenerRemover;

import org.json.JSONObject;

import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * flutter_boost 与  原生交互
 */
public class FluterEventManager {

    private static volatile FluterEventManager instance;
    private FluterEventManager() {}
    public static FluterEventManager getInstance() {
        if (instance == null) {
            synchronized (FluterEventManager.class) {
                if (instance == null) {
                    instance = new FluterEventManager();
                }
            }
        }
        return instance;
    }

    private ListenerRemover remover;

    /**----------------------initBoostEvent----------------------------*/
    public void initBoostEvent(){
        if (remover == null) {
            EventListener listener = (key, args) -> {
                try {
                    JSONObject jsonObject = new JSONObject(args);
                    Log.e("NativeApp  map参数" , jsonObject.toString());
                    String event = jsonObject.getString("event");

                    String name = jsonObject.getString("name");
                    int type = jsonObject.getInt("age");

//                    Log.e("NativeApp  map参数" , "event = " +event +"   name = " +name+"  type = " +type);
//                    Map map = new HashMap();
//                    map.put("type",type);
//
//                    switch (type){
//                        case 1:
//                            break;
//                        case 2:
//                            break;
//                        default:
//                            break;
//                    }
//                    if (type % 2 == 0) {
//                        FlutterBoost.instance().sendEventToFlutter("eventToFlutter",map);
//                    } else {
//                        FlutterBoost.instance().sendEventToFlutter("eventToFlutter",map);
//                    }


                } catch (Exception e) {
                    Log.e("NativeApp" , "解析失败");
                    e.printStackTrace();
                }

            };
            remover = FlutterBoost.instance().addEventListener("eventToNative", listener);
        }
    }

    public void releaseBoostEvent(){
        remover.remove();
        if (remover != null){
            remover = null;
        }
    }

    /**----------------------    initFluuter    ----------------------------*/
    private FlutterEngine flutterEngine;
    public void initFlutter(Application application){
        initFlutterEngineCache(application);

        initFlutterBoost(application);

        FluterEventManager.getInstance().initBoostEvent();
    }

    public void initFlutterEngineCache(Application application) {
        flutterEngine = new FlutterEngine(application);
//        可设置初始路由
        flutterEngine.getNavigationChannel().setInitialRoute("flutter:///main/main");
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FlutterEngineCache
                .getInstance()
                .put("flutter:///main/main", flutterEngine);
    }

    private void initFlutterBoost(Application application) {
        FlutterBoost.instance().setup(application, new FlutterBoostDelegate() {
            @Override
            public void pushNativeRoute(FlutterBoostRouteOptions options) {
                //这里根据options.pageName来判断想跳转哪个页面
                String pageName = options.pageName();
//                ARouter.getInstance().build("/app/MessageTargetActivity").withString("pageUrl",pageName).navigation();
            }

            @Override
            public void pushFlutterRoute(FlutterBoostRouteOptions options) {
                Log.e("NativeApp", "pushFlutterRoute:");
                Intent intent = new DaiMaoFlutterActivity.CachedEngineIntentBuilder(DaiMaoFlutterActivity.class)
                        .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                        .destroyEngineWithActivity(false)
                        .uniqueId(options.uniqueId())
                        .url(options.pageName())
                        .urlParams(options.arguments())
                        .build(FlutterBoost.instance().currentActivity());
                FlutterBoost.instance().currentActivity().startActivity(intent);
            }
        }, engine -> {
        });
    }

}
