package com.lpc.testgradle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lpc.testgradle.hook.HookUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HookUtil(this).hookAMS();

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // new MyException("test exception").printStackTrace();

               MainActivity.this.startActivity(new Intent(MainActivity.this, TestActivity.class));
           }
        });

    }

}
