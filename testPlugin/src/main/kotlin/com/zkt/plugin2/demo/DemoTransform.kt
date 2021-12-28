package com.zkt.plugin2.demo

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class DemoTransform : Transform(), Plugin<Project> {

    override fun apply(project: Project) {
        println(">>>>>> 1.1.1 this is a log just from DemoTransform")
        // val appExtension = project.extensions.getByType(AppExtension::class.java)
        // appExtension.registerTransform(this)
    }

    override fun getName(): String {
        return "KotlinDemoTransform"
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

        //消费型输入，可以从中获取jar包和class文件夹路径。需要输出给下一个任务
        val inputs = transformInvocation.inputs

        //OutputProvider管理输出路径，如果消费型输入为空，你会发现OutputProvider == null
        val outputProvider = transformInvocation.outputProvider


        for (input in inputs) {
            for (jarInput in input.jarInputs) {
                val dest = outputProvider.getContentLocation(
                        jarInput.file.absolutePath,
                        jarInput.contentTypes,
                        jarInput.scopes,
                        Format.JAR)
                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
                transformJar(jarInput.file, dest)
            }

            for (directoryInput in input.directoryInputs) {
                println("== DI = " + directoryInput.file.listFiles().toString())
                val dest = outputProvider.getContentLocation(
                        directoryInput.name,
                        directoryInput.contentTypes,
                        directoryInput.scopes,
                        Format.DIRECTORY)

                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
                //FileUtils.copyDirectory(directoryInput.getFile(), dest)
                transformDir(directoryInput.getFile(), dest)
            }
        }

    }


    fun transformJar(input: File, dest: File) {
        println("=== transformJar ===")
        FileUtils.copyFile(input, dest)
    }


    fun transformDir(input: File, dest: File) {
        if (dest.exists()) {
            FileUtils.forceDelete(dest)
        }
        FileUtils.forceMkdir(dest)
        val srcDirPath = input.absolutePath
        val destDirPath = dest.absolutePath
        println("=== transform dir = $srcDirPath, $destDirPath")
        for (file in input.listFiles()) {
            val destFilePath = file.absolutePath.replace(srcDirPath, destDirPath)
            val destFile = File(destFilePath)
            if (file.isDirectory) {
                transformDir(file, destFile)
            } else if (file.isFile) {
                FileUtils.touch(destFile)
                transformSingleFile(file, destFile)
            }
        }
    }

    fun transformSingleFile(input: File, dest: File) {
        println("=== transformSingleFile ===")
        weave(input.absolutePath, dest.absolutePath)
    }

    fun weave(inputPath: String, outputPath: String) {
        try {
            val `is` = FileInputStream(inputPath)
            val cr = ClassReader(`is`)
            val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES)
            val adapter = TestMethodClassAdapter(cw)
            cr.accept(adapter, 0)
            val fos = FileOutputStream(outputPath)
            fos.write(cw.toByteArray())
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    class TestMethodClassAdapter(classVisitor: ClassVisitor?) : ClassVisitor(Opcodes.ASM5, classVisitor), Opcodes {

        override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<String?>?): MethodVisitor {
            val mv: MethodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
            return TestMethodVisitor(mv)
        }
    }

    class TestMethodVisitor(methodVisitor: MethodVisitor?) : MethodVisitor(Opcodes.ASM5, methodVisitor) {

        override fun visitMethodInsn(opcode: Int, owner: String, name: String, descriptor: String?, isInterface: Boolean) {
            println("== TestMethodVisitor, owner = $owner, name = $name")

            //方法执行之前打印
            mv.visitLdcInsn(" before method exec")
            mv.visitLdcInsn(" [ASM 测试] method in $owner ,name=$name")
            mv.visitMethodInsn(Opcodes.INVOKESTATIC,
                    "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false)
            mv.visitInsn(Opcodes.POP)

            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)

            //方法执行之后打印
            mv.visitLdcInsn(" after method exec")
            mv.visitLdcInsn(" method in $owner ,name=$name")
            mv.visitMethodInsn(Opcodes.INVOKESTATIC,
                    "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false)
            mv.visitInsn(Opcodes.POP)
        }
    }

}
