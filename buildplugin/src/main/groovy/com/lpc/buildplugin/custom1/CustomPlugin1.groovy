package com.lpc.buildplugin.custom1

import com.lpc.buildplugin.custom1.ext.CustomPlugin1Ext
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin1 implements Plugin<Project> {

    private String TASK_GROUP = 'custom'

    @Override
    void apply(Project project) {

        project.task('customPlugin1') {
            doLast {
                println('task in customPlugin1')
            }
        }.setGroup(TASK_GROUP)


        def custom1ext = project.extensions.create('customPlugin1Ext', CustomPlugin1Ext)

        project.task('customPlugin1Ext') {
            doLast {
                println("task in $custom1ext.msg")
            }
        }.setGroup(TASK_GROUP)

    }
}
