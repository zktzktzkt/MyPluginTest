package com.asm.demo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.lpc.testgradle.R;


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