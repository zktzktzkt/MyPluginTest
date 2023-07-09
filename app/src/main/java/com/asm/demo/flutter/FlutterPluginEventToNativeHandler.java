package com.asm.demo.flutter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * methodchannel 与flutter交互实现类
 */
public class FlutterPluginEventToNativeHandler implements MethodChannel.MethodCallHandler {

    private MethodChannel.Result mResult;
    private String mCurrentType;
    int batteryLevel = 0;
    private FlutterBoostActivity context;
    //分享，图片加载失败后，防止重复
    boolean shared = false;

    public FlutterPluginEventToNativeHandler(FlutterBoostActivity context) {
        this.context = context;
    }

    public MethodChannel.Result getResult(String currentType) {
        //防止错乱，当前的call.method要跟result匹配上才能用，要不然会错乱
        if (mCurrentType != null && mCurrentType.equals(currentType)) {
            return mResult;
        } else {
            return null;
        }
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Log.e("NativeApp", call.method);
        mResult = result;
        mCurrentType = call.method;
        switch (call.method) {
            case "getBatteryLevel":
                doGetBatteryLevel(result);
                break;
            case "getToastMethod":
                doGetToastMethod(result);
                break;
            case "getJsonMethod":
                getJsonMethod(result);
                break;
            case "getAppVersion":
                getVersionName(result);
                break;
            default:
                result.notImplemented();
                break;
        }
    }


    private void doGetBatteryLevel(MethodChannel.Result result) {
        batteryLevel++;
        result.success(batteryLevel);
        Toast.makeText(context, "Native getBatteryLevel()", Toast.LENGTH_LONG).show();
    }

    private void doGetToastMethod(MethodChannel.Result result) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Native getToastMethod()", Toast.LENGTH_LONG).show();
                result.error("UNAVAILABLE", "Battery level not available.", null);

            }
        }, 1000);

    }

    private void getJsonMethod(MethodChannel.Result result) {
        String jsonArr = "[{\"cityStationId\":\"824358401244008409\",\"cityName\":\"杭州市\",\"state\":1,\"secondCity\":\"杭州市\",\"districtName\":null},{\"cityStationId\":\"824358401244008410\",\"cityName\":\"北京市\",\"state\":1,\"secondCity\":\"北京市\",\"districtName\":null},{\"cityStationId\":\"824358401244008411\",\"cityName\":\"成都市\",\"state\":1,\"secondCity\":\"成都市\",\"districtName\":null}]";
        String jsonObj = "{\"list\":[{\"cityStationId\":\"824358401244008409\",\"cityName\":\"杭州市\",\"state\":1,\"secondCity\":\"杭州市\",\"districtName\":null},{\"cityStationId\":\"824358401244008410\",\"cityName\":\"北京市\",\"state\":1,\"secondCity\":\"北京市\",\"districtName\":null},{\"cityStationId\":\"824358401244008411\",\"cityName\":\"成都市\",\"state\":1,\"secondCity\":\"成都市\",\"districtName\":null}]}";
        result.success(jsonArr);


//        获取网络数据
//        对象json解析方式{'key':[{},{}]}
//                    JSONArray jsonArray = new JSONArray(new Gson().toJson(cityList));
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("list", jsonArray);
//                    Log.e("NativeApp", "    json = " +jsonObject.toString());
//                    result.success(jsonObject.toString());

//                    数据json解析方式 [{},{}]
//        JSONArray jsonArray = new JSONArray(new Gson().toJson(cityList));
//        result.success(jsonArray.toString());

    }

    /**
     * 获取app版本号
     *
     * @param result
     */
    private void getVersionName(MethodChannel.Result result) {
        String versionName;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (Exception e) {
            versionName = "1.0.1";
        }
        result.success(versionName);
    }

}