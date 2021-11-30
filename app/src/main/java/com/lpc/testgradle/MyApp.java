package com.lpc.testgradle;

import android.app.Application;

/**
 * Created by zhoukaitong on 2021/11/30.
 * Description:
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // new HookUtil(this).hookAMS();
    }
}
