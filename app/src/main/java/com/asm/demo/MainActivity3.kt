package com.zkt.ams.demo

import android.content.Context
import android.util.Log

/**
 * Created by zhoukaitong on 2021/12/28.
 * Description:
 */
class MainActivity3 {

    fun test1(context: Context) {
        val packageName = context.packageName
    }

    fun test2(name: String, age: String) {
        Log.e("jajaha", "$name $age")
    }
}