package com.asm.demo;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.lpc.testgradle.R;

public class MyFlutterActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flutter);
    }

}