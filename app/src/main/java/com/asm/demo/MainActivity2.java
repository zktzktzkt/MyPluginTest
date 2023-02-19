package com.asm.demo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.lpc.testgradle.R;

import java.util.List;


public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onThread();
    }

    protected void onThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("替换线程测试", "执行啦");
            }
        }).start();
    }

    protected void onShadowThread() {
        new ShadowThread(null).start();
    }


    public static PackageManager getPackageManager(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager;
    }

    /**
     * 替换对象方法
     */
    public static int getInstallPkgCount(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        int count = packageInfos.size();
        return count;
    }

    /**
     * 替换对象属性
     */
    public static void getDensity(Context context) {
        Resources resources = context.getResources();
        float density = resources.getDisplayMetrics().density;
    }

    static String aaa = "10";

    /**
     * 替换静态方法
     */
    public static void getRadioVersion() {
        String radioVersion = Build.getRadioVersion();
        Log.e("haha", aaa);
    }

    /**
     * 替换静态属性
     */
    public static void getBrand() {
        String brand = Build.BRAND;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}