package com.lpc.testgradle;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dalvik.system.DexFile;

/**
 * Created by zhoukaitong on 2021/12/1.
 * Description:
 */
public class ClassUtils {

    /**
     * 获取当前应用符合com.xxx.xxx包名的所有类名
     */
    public static Set<String> getFileNameByPackageName(Application context, final String packageName) throws PackageManager.NameNotFoundException {

        //拿到apk文件路径（比如：data/app/com.xxx.xxx-Xa9IIaUJgMJWmSrX4_7Btg==/base.apk）
        List<String> paths = getSourcePaths(context);

        final Set<String> classNames = new HashSet<>();

        for (String path : paths) {
            DexFile dexFile = null;
            try {
                //加载apk中的所有dex，并遍历 获得所有 packageName 的类
                dexFile = new DexFile(path);
                Enumeration<String> dexEntries = dexFile.entries();
                while (dexEntries.hasMoreElements()) {
                    //com.xxx.xxx.XXX 整个apk中所有的类
                    String className = dexEntries.nextElement();
                    if (className.startsWith(packageName)) {
                        classNames.add(className);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != dexFile) {
                    try {
                        dexFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

         /*[com.lpc.testgradle.MyApp,
            com.lpc.testgradle.hook.IHook,
            com.lpc.testgradle.BuildConfig,
            com.lpc.testgradle.R$integer] */
        return classNames;
    }


    /**
     * 获取当前应用的所有的apk
     */
    private static List<String> getSourcePaths(Context context) throws PackageManager.NameNotFoundException {

        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);

        // Android5.0引入了Split APK机制，这是为了解决65536上限以及APK安装包越来越大等问题。Split APK机制可以将一个APK，拆分成多个独立APK。
        // 在引入了Split APK机制后，APK有两种分类：
        // Single APK：安装文件为一个完整的APK，即base APK。Android称其为Monolithic。
        // Mutiple APK：安装文件在一个文件目录中，其内部有多个被拆分的APK，这些APK由一个 base APK和一个或多个split APK组成。Android称其为Cluster。

        List<String> sourcePaths = new ArrayList<>();
        //当前应用的apk文件路径
        sourcePaths.add(applicationInfo.sourceDir);
        //instant run
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (null != applicationInfo.splitSourceDirs) {
                sourcePaths.addAll(Arrays.asList(applicationInfo.splitSourceDirs));
            }
        }

        return sourcePaths;
    }

}
