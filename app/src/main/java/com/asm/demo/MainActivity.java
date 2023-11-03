package com.asm.demo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.lpc.testgradle.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoodsTagLayout layoutGoodsTag = findViewById(R.id.layoutGoodsTag);

        List<GoodsTagBean> list = new ArrayList<>();

        GoodsTagBean bean1 = new GoodsTagBean();
        bean1.setYAxis(300);
        bean1.setXAxis(300);
        bean1.setDirection("l");
        list.add(bean1);

        GoodsTagBean bean2 = new GoodsTagBean();
        bean2.setYAxis(500);
        bean2.setXAxis(500);
        bean2.setDirection("t");
        list.add(bean2);
//
        GoodsTagBean bean3 = new GoodsTagBean();
        bean3.setYAxis(700);
        bean3.setXAxis(700);
        bean3.setDirection("r");
        list.add(bean3);
//
        GoodsTagBean bean4 = new GoodsTagBean();
        bean4.setYAxis(900);
        bean4.setXAxis(900);
        bean4.setDirection("b");
        list.add(bean4);

        layoutGoodsTag.setGoodsTags(list);

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