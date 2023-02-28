package com.asm.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.lpc.testgradle.R;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;


public class MainActivity extends AppCompatActivity {

    //FlutterEngine
    private FlutterEngine flutterEngine;
    public static final String FLUTTER_ENGINE_ID = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化 FlutterEngine
        flutterEngine = initFlutterEngine(FLUTTER_ENGINE_ID);

        //搞一个耗时操作
        String test = "";
        for (int i = 0; i < 10000; i++) {
            test += i;
        }
    }

    /**
     * 初始化 FlutterEngine
     * 一般在跳转前调用，从缓存中取出 FlutterEngine，这样可以加快我们页面的一个跳转
     */
    private FlutterEngine initFlutterEngine(String engineId) {
        //创建 FlutterEngine
        FlutterEngine flutterEngine = new FlutterEngine(this);
        //指定要跳转的 Flutter 页面
        flutterEngine.getNavigationChannel().setInitialRoute("main");
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        //缓存 FlutterEngine
        FlutterEngineCache flutterEngineCache = FlutterEngineCache.getInstance();
        flutterEngineCache.put(engineId,flutterEngine);
        return flutterEngine;
    }

    /**
     * 点击跳转flutter页面
     * @param view
     */
    public void onCLickLoadFlutter(View view) {
//        Intent intent = FlutterActivity.withNewEngine()
//                .initialRoute("main")
//                .build(this);
//        startActivity(intent);

        startActivity(new Intent(this, MyFlutterActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new MainActivity3().test1(MainActivity.this);
                StatusBarUtil.setLightMode(MainActivity.this);
            }
        });

    }

    @Override
    protected void onResume() {
        long pre = System.currentTimeMillis();
        super.onResume();
        long cosetime = System.currentTimeMillis() - pre;
        Log.e("方法耗时", cosetime + "ms");
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
        /**
         * 注意这里一定要销毁，否则会导致内存泄漏
         * 因为 FlutterEngine 比显示它的 FlutterActivity 生命周期要长
         * 当我们退出 FlutterActivity 时，FlutterEngine 可能还会继续执行代码
         * 所以我们应该在 FlutterActivity 退出时调用 flutterEngine.destroy 停止执行并释放资源
         */
        flutterEngine.destroy();
    }

    public void onHhHAHAH(Context context, String name, String age) {
        Log.i("zkt", "onHhHAHAH");
    }

    public void qqq(Context context) {
        Log.i("zkt", "qqq");
    }

    public void wwww(Context context, int aa) {
        Log.i("zkt", "wwww");
    }


    public void rrrrr() {

        Log.i("zkt", "rrrrr");

    }


}