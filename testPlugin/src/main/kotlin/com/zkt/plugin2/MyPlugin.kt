package com.zkt.plugin2

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 * Created by zhoukaitong on 2021/11/11.
 * Description:
 */
class MyPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val tempFile = initDir(project)
        //
        val app = project.extensions.findByType(AppExtension::class.java)
        app?.registerTransform(MyTransform(project, tempFile))

        //创建extension
        val myExt = project.extensions.create("myExt", MyExtension::class.java)

        // 所有Project配置完成后，会回调project.afterEvaluate()
        project.afterEvaluate {
            it.tasks.create("zktPlugin", MyTask::class.java, myExt.key1, myExt.key2)
                    .group = "zktPluginGroup"
        }
    }

    fun initDir(project: Project): File {
        if (!project.buildDir.exists()) {
            project.buildDir.mkdirs()
        }
        val tempFile = File(project.buildDir, "ams")
        if (!tempFile.exists()) {
            tempFile.mkdir()
        }
        return tempFile
    }
}