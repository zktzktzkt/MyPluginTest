package com.zkt.plugin2

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.zkt.plugin2.asm.TimeClassVisitor
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.io.File
import java.io.FileOutputStream
import java.util.*
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * Created by zhoukaitong on 2021/11/11.
 * Description:
 */
class MyTransform(val project: Project, val tempFile: File) : Transform() {

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

            //使用androidx的项目一定也注意jar也需要处理，否则所有的jar都不会最终编译到apk中，千万注意
            //导致出现ClassNotFoundException的崩溃信息，当然主要是因为找不到父类，因为父类AppCompatActivity在jar中
            for (jarInput in input.jarInputs) {
                println("jar=" + jarInput.name)
                handleJarInputs(jarInput, outputProvider)
            }

            for (directoryInput in input.directoryInputs) {
                handleDirectoryInput(directoryInput, outputProvider)
            }
        }
    }

    /**
     * 处理文件目录下的class文件
     */
    fun handleDirectoryInput(directoryInput: DirectoryInput, outputProvider: TransformOutputProvider) {
        //是否是目录
        if (directoryInput.file.isDirectory) {
            //列出目录所有文件（包含子文件夹，子文件夹内文件）
            for (file in FileUtils.getAllFiles(directoryInput.file)) {
                if (filterClass(file.name)) {
                    val classReader = ClassReader(file.readBytes())
                    val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES)
                    val cv: ClassVisitor = TimeClassVisitor(Opcodes.ASM5, classWriter)
                    classReader.accept(cv, ClassReader.EXPAND_FRAMES)
                    val code = classWriter.toByteArray()
                    val fos = FileOutputStream(file.parentFile.absolutePath.toString() + File.separator + name)
                    fos.write(code)
                    fos.close()
                    saveModifiedClassForCheck(file)
                }
            }
        }
        //处理完输入文件之后，要把输出给下一个任务
        val dest = outputProvider.getContentLocation(directoryInput.name,
                directoryInput.contentTypes, directoryInput.scopes,
                Format.DIRECTORY)
        FileUtils.copyDirectory(directoryInput.file, dest)
    }

    /*
    保存插桩后的文件到临时目录 方便查看是否插桩正确
     */
    fun saveModifiedClassForCheck(tempClass: File) {
        val dir: File = tempFile
        val checkJarFile = File(dir, tempClass.name.replace("/", "_"))
        if (checkJarFile.exists()) {
            checkJarFile.delete()
        }
        FileUtils.copyFile(tempClass, checkJarFile)
    }

    /**
     * 处理Jar中的class文件
     */
    fun handleJarInputs(jarInput: JarInput, outputProvider: TransformOutputProvider) {
        if (jarInput.file.absolutePath.endsWith(".jar")) {
            //重名名输出文件,因为可能同名,会覆盖
            var jarName = jarInput.name
            val md5Name = DigestUtils.md5Hex(jarInput.file.absolutePath)
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length - 4)
            }
            val jarFile = JarFile(jarInput.file)
            val enumeration = jarFile.entries()
            val tmpFile = File(jarInput.file.parent + File.separator + "classes_temp.jar")
            //避免上次的缓存被重复插入
            if (tmpFile.exists()) {
                tmpFile.delete()
            }
            val jarOutputStream = JarOutputStream(FileOutputStream(tmpFile))
            //用于保存
            while (enumeration.hasMoreElements()) {
                val jarEntry = enumeration.nextElement()
                val entryName = jarEntry.name
                val zipEntry = ZipEntry(entryName)
                val inputStream = jarFile.getInputStream(jarEntry)
                //插桩class
                if (filterClass(entryName)) {
                    //class文件处理
                    // println ('----------- deal with "jar" class file <'+entryName+'> -----------')
                    jarOutputStream.putNextEntry(zipEntry)
                    val classReader = ClassReader(inputStream.readBytes())
                    val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES)
                    val cv = TimeClassVisitor(Opcodes.ASM5, classWriter)
                    classReader.accept(cv, ClassReader.EXPAND_FRAMES)
                    val code = classWriter.toByteArray()
                    jarOutputStream.write(code)
                } else {
                    jarOutputStream.putNextEntry(zipEntry)
                    jarOutputStream.write(inputStream.readBytes())
                }
                jarOutputStream.closeEntry()
            }
            //结束
            jarOutputStream.close()
            jarFile.close()
            val dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
            FileUtils.copyFile(tmpFile, dest)
            tmpFile.delete()
        }
    }

    //////////////////////////////////////////

    /**
     * 检查class文件是否需要处理
     * @param fileName
     * @return
     */
    fun filterClass(name: String): Boolean {
        return (name.endsWith(".class")
                && name != ("R.class")
                && !name.startsWith("R\$")
                && name != ("BuildConfig.class")
                && name.contains("Activity")
                && !name.contains("android/"))
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
        // 将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
        FileUtils.copyFile(jarInput.file, dest)
    }

    fun processDirectoryInputs(directoryInput: DirectoryInput, outputProvider: TransformOutputProvider) {

        //文件夹里面包含的是我们手写的类以及R.class、BuildConfig.class以及R$XXX.class等
        // 获取output目录
        val dest = outputProvider.getContentLocation(
                directoryInput.name,
                directoryInput.contentTypes,
                directoryInput.scopes,
                Format.DIRECTORY)
        // 建立文件夹
        FileUtils.mkdirs(dest)
        //这里执行字节码的注入，不操作字节码的话也要将输入路径拷贝到输出路径
        FileUtils.copyDirectory(directoryInput.file, dest)
    }

}