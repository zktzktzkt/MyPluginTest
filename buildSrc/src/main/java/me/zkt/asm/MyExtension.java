package me.zkt.asm;

import java.util.ArrayList;

public class MyExtension {

    public static boolean isShowDebugInfo = false;
    /**
     * 需要排除插桩的包名
     */
    public ArrayList<String> thirdPackage = new ArrayList<>();

    public MyExtension() {
    }
}
