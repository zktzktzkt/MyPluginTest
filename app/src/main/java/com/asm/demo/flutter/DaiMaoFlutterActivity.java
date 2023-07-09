package com.asm.demo.flutter;

import android.content.Intent;
import android.os.Bundle;

import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class DaiMaoFlutterActivity extends FlutterBoostActivity {

    private static final String METHOD_CHANNEL = "flutter_daimao.flutter.io/method_channel";
    private FlutterPluginEventToNativeHandler flutterPluginEventToNativeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(0);
        initChannel();
    }

    private void initChannel() {
        BinaryMessenger binaryMessenger = getFlutterEngine().getDartExecutor().getBinaryMessenger();
        MethodChannel nativeChannel = new MethodChannel(binaryMessenger, METHOD_CHANNEL);
        flutterPluginEventToNativeHandler = new FlutterPluginEventToNativeHandler(DaiMaoFlutterActivity.this);
        nativeChannel.setMethodCallHandler(flutterPluginEventToNativeHandler);
    }

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        GeneratedPluginRegistrant.registerWith(flutterEngine);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (requestCode == 1001) {//相册
            if (data != null) {
                String photoList = data.getStringExtra("data");
                MethodChannel.Result result = flutterPluginEventToNativeHandler.getResult(ConstantChannel.INSURANCE_SELECT_PHOTO);
                if (result != null) {
                    result.success(photoList);
                }
            }
        }else if(requestCode == 1002){//地址
            if (data != null) {
                String address = data.getStringExtra("address");
                int type = data.getIntExtra("type",0);
                StringBuilder stringBuilder = new StringBuilder();
                if(type ==1){
                    AMapLocation mapLocation =  data.getParcelableExtra("addressVO");
                    stringBuilder.append(mapLocation.getProvince());
                    stringBuilder.append(mapLocation.getCity());
                    stringBuilder.append(mapLocation.getDistrict());
                }else {
                    PoiItem mapLocation =  data.getParcelableExtra("addressVO");
                    stringBuilder.append(mapLocation.getProvinceName());
                    stringBuilder.append(mapLocation.getCityName());
                    stringBuilder.append(mapLocation.getAdName());
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("region",stringBuilder.toString());
                jsonObject.put("address",address);
//                AMapLocation mapLocation = data.getParcelableExtra<AMapLocation>("addressVO");
                MethodChannel.Result result = flutterPluginEventToNativeHandler.getResult(ConstantChannel.INSURANCE_SELECT_ADDRESS);
                if (result != null) {
                    result.success(jsonObject.toString());
                }
            }
        }*/
    }
}
