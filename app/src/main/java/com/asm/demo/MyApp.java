package com.asm.demo;

import android.app.Application;

import com.asm.demo.flutter.FluterEventManager;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FluterEventManager.getInstance().initFlutter(this);
    }
}
