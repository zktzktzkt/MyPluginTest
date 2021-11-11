package com.zkt.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class MyTask extends DefaultTask {

    String key = ""
    String key2 = ""

    // 添加构造参数
    @Inject
    MyTask(String key, String key2) {
        this.key = key
        this.key2 = key2
    }

    // 添加要执行动作
    @TaskAction
    def onTaskMethod() {
        println "====== key : $key  === key2 : $key2 ============="
    }
}