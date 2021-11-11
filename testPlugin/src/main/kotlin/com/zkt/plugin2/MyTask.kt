package com.zkt.plugin2

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

/**
 * Created by zhoukaitong on 2021/11/11.
 * Description:
 */

open class MyTask() : DefaultTask() {

    private var key: String = ""
    private var key2: String = ""

    @Inject
    constructor(key: String, key2: String) : this() {
        this.key = key
        this.key2 = key
    }

    // 添加要执行动作
    @TaskAction
    fun onTaskMethod() {
        println("MyTask ====== key : $key  === key2 : $key2 =============")
    }
}