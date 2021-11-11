package com.zkt.plugin2

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.gradle.api.Project
import java.io.File

/**
 * Created by zhoukaitong on 2021/11/11.
 * Description:
 */
class MyTransform(val project: Project) : Transform() {

    override fun getName(): String {
        return "MyTransform"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)

        printCopyRight()

        val outputProvider = transformInvocation.outputProvider as TransformOutputProvider

        for (input in transformInvocation.inputs) {

            for (jarInput in input.jarInputs) {
                println("jar=" + jarInput.name)
                processJarInput(jarInput, outputProvider)
            }

            for (directoryInput in input.directoryInputs) {
                if (directoryInput.file.isDirectory) {
                    for (file in FileUtils.getAllFiles(directoryInput.file)) {
                        println("file=" + file.name)
                    }
                }
                processDirectoryInputs(directoryInput, outputProvider)
            }
        }
    }


    fun printCopyRight() {
        println()
        println("******************************************************************************")
        println("******                                                                  ******")
        println("******                欢迎使用 RenxhTransform 编译插件                    ******")
        println("******                                                                  ******")
        println("******************************************************************************")
        println()
    }

    fun processJarInput(jarInput: JarInput, outputProvider: TransformOutputProvider) {
        val dest = outputProvider.getContentLocation(
                jarInput.file.absolutePath,
                jarInput.contentTypes,
                jarInput.scopes,
                Format.JAR)

        // to do some transform

        // 将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
        FileUtils.copyFile(jarInput.file, dest)
    }

    fun processDirectoryInputs(directoryInput: DirectoryInput, outputProvider: TransformOutputProvider) {
        val dest = outputProvider.getContentLocation(
                directoryInput.name,
                directoryInput.contentTypes,
                directoryInput.scopes,
                Format.DIRECTORY)
        // 建立文件夹
        FileUtils.mkdirs(dest)

        // to do some transform

        // 将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
        FileUtils.copyDirectory(directoryInput.file, dest)
    }

}