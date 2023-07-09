package com.asm.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.asm.demo.flutter.FlutterJumpManager;
import com.jaeger.library.StatusBarUtil;
import com.lpc.testgradle.R;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //搞一个耗时操作
        String test = "";
        for (int i = 0; i < 10000; i++) {
            test += i;
        }
    }

    /**
     * 点击跳转flutter页面
     * @param view
     */
    public void onCLickLoadFlutter(View view) {
        FlutterJumpManager.jumpMain(this);
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
//        flutterEngine.destroy();
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