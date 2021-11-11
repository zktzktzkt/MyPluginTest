package com.zkt.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.gradle.api.Project


class MyTransform extends Transform {

    Project mProject


    MyTransform(Project project) {
        this.mProject = project
    }

    @Override
    String getName() {
        return "MyTransform"
    }

    /**
     * 需要处理的数据类型，有两种枚举类型
     * CLASSES 代表处理的 java 的 class 文件，RESOURCES 代表要处理 java 的资源
     * @return
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 指 Transform 要操作内容的范围，官方文档 Scope 有 7 种类型：
     * 1. EXTERNAL_LIBRARIES        只有外部库
     * 2. PROJECT                   只有项目内容
     * 3. PROJECT_LOCAL_DEPS        只有项目的本地依赖(本地jar)
     * 4. PROVIDED_ONLY             只提供本地或远程依赖项
     * 5. SUB_PROJECTS              只有子项目。
     * 6. SUB_PROJECTS_LOCAL_DEPS   只有子项目的本地依赖项(本地jar)。
     * 7. TESTED_CODE               由当前变量(包括依赖项)测试的代码
     * @return
     */
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        printCopyRight()

        TransformOutputProvider transformOutputProvider = transformInvocation.getOutputProvider()

        transformInvocation.getInputs().each { TransformInput transformInput ->

            transformInput.jarInputs.each { JarInput jarInput ->
                println("jar=" + jarInput.name)
                processJarInput(jarInput, transformOutputProvider)
            }

            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                if (directoryInput.file.isDirectory()) {
                    FileUtils.getAllFiles(directoryInput.file).each { File file ->
                        println(file.name)
                    }
                }
                processDirectoryInputs(directoryInput, transformOutputProvider)
            }

        }
    }


    static void printFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles()
            files.each { File file1 ->
                if (file1.isDirectory()) {
                    printFile(file1)
                } else {
                    println("File = " + file.name)
                }
            }
        } else {
            println("File = " + file.name)
        }
    }

    static void printCopyRight() {
        println()
        println("******************************************************************************")
        println("******                                                                  ******")
        println("******                欢迎使用 RenxhTransform 编译插件                    ******")
        println("******                                                                  ******")
        println("******************************************************************************")
        println()
    }

    static void processJarInput(JarInput jarInput, TransformOutputProvider outputProvider) {
        File dest = outputProvider.getContentLocation(
                jarInput.getFile().getAbsolutePath(),
                jarInput.getContentTypes(),
                jarInput.getScopes(),
                Format.JAR)

        // to do some transform

        // 将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
        FileUtils.copyFile(jarInput.getFile(), dest)
    }

    static void processDirectoryInputs(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
        File dest = outputProvider.getContentLocation(directoryInput.getName(),
                directoryInput.getContentTypes(), directoryInput.getScopes(),
                Format.DIRECTORY)
        // 建立文件夹
        FileUtils.mkdirs(dest)

        // to do some transform

        // 将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
        FileUtils.copyDirectory(directoryInput.getFile(), dest)
    }
}
