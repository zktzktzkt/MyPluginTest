package com.lpc.testgradle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        new MyException("test exception").printStackTrace();
        //    }
        //});

    }

}
