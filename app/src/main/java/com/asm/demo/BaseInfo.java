package com.asm.demo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhoukaitong on 2022/2/24.
 * Description:
 */
public class BaseInfo {

    public static String getRadioVersion() {
        return "456";
    }

    public static String getDeviceBrand() {
        return "123";
    }

    public static float getDensity() {
        return 1.23f;
    }

    public static List<PackageInfo> getInstalledPkgsWithAOP(PackageManager packageManager, int flag) {
        return BaseInfo.getInstalledPkgs(flag);
    }

    public static List<PackageInfo> getInstalledPkgs(int flag) {
        return Collections.emptyList();
    }


    /****************************************************************
     *                          测 试
     ***************************************************************/

    public static PackageManager getPackageManager() {
        return null;
    }

    public static String getAAA() {
        return "null";
    }

}
