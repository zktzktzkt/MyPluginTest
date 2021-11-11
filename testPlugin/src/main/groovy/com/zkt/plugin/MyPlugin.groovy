package com.zkt.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    void apply(Project project) {

        //注册自定的transform
        AppExtension extension = project.getExtensions().findByType(AppExtension.class)
        extension.registerTransform(new MyTransform(project))

        //创建extension
        def obj = project.extensions.create("test111", MyExtension)

        // 所有Project配置完成后，会回调project.afterEvaluate()
        project.afterEvaluate(new Action<Project>() {
            @Override
            void execute(Project p) {
                //创建task
                project.getTasks().create("zkt", MyTask, obj.key1, obj.key2).setGroup('zktGroup')
            }
        })
    }
}