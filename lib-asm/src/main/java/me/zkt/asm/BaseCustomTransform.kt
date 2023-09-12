package me.zkt.asm

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.builder.utils.isValidZipEntryName
import com.android.utils.FileUtils
import com.google.common.io.Files
import org.gradle.api.Project
import org.gradle.internal.impldep.bsh.commands.dir
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

abstract class BaseCustomTransform(private val enableLog: Boolean) : Transform() {

    /**
     * project对象
     */
    private var project: Project ?= null

    /**
     * 修改class后存放的文件
     */
    private var tempFile: File? = null

    //线程池，可提升 80% 的执行速度
    private var waitableExecutor: WaitableExecutor = WaitableExecutor.useGlobalSharedThreadPool()

    /**
     * 此方法提供给上层进行字节码插桩
     */
    abstract fun provideFunction(): ((InputStream, OutputStream) -> Unit)?

    /**
     * 上层可重写该方法进行文件过滤
     */
    open fun classFilter(className: String) = checkClassFile(className)


    /**
     * 默认：获取输入的字节码文件
     */
    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 默认：检索整个项目的内容
     */
    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 默认开启增量编译
     */
    override fun isIncremental(): Boolean {
        return true
    }

    /**
     * 初始化插桩文件的临时目录，子类调用（方便查看是否插桩正确）
     */
    fun initDir(project: Project) {
        this.project = project
        if (!project.buildDir.exists()) {
            project.buildDir.mkdirs()
        }
        tempFile = File(project.buildDir, "asm")
        tempFile?.let {
            if (!it.exists()) {
                it.mkdir()
            }
        }
    }

    /**
     * 对输入的数据做检索操作：
     * 1、处理增量编译
     * 2、处理并发逻辑
     */
    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)

        log("Transform start...")

        //输入内容
        val inputProvider = transformInvocation.inputs
        //输出内容
        val outputProvider = transformInvocation.outputProvider

        // 1. 子类实现字节码插桩操作
        val function = provideFunction()

        // 2. 不是增量编译，删除所有旧的输出内容
        if (!transformInvocation.isIncremental) {
            outputProvider.deleteAll()
        }

        for (input in inputProvider) {
            // 3. Jar 包处理
            log("Transform jarInputs start.")
            for (jarInput in input.jarInputs) {
                val inputJar = jarInput.file
                val outputJar = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                if (transformInvocation.isIncremental) {
                    // 3.1. 增量编译中处理 Jar 包逻辑
                    when (jarInput.status ?: Status.NOTCHANGED) {
                        Status.NOTCHANGED -> {
                            // Do nothing.
                        }
                        Status.ADDED, Status.CHANGED -> {
                            // Do transform.
                            waitableExecutor.execute {
                                doTransformJar(inputJar, outputJar, function)
                            }
                        }
                        Status.REMOVED -> {
                            // Delete.
                            FileUtils.delete(outputJar)
                        }
                    }
                } else {
                    // 3.2 非增量编译中处理 Jar 包逻辑
                    waitableExecutor.execute {
                        doTransformJar(inputJar, outputJar, function)
                    }
                }
            }
            // 4. 文件夹处理
            log("Transform dirInput start.")
            for (dirInput in input.directoryInputs) {
                val inputDir = dirInput.file
                val outputDir = outputProvider.getContentLocation(dirInput.name, dirInput.contentTypes, dirInput.scopes, Format.DIRECTORY)
                if (transformInvocation.isIncremental) {
                    // 4.1. 增量编译中处理文件夹逻辑
                    for ((inputFile, status) in dirInput.changedFiles) {
                        val outputFile = concatOutputFilePath(outputDir, inputFile)
                        when (status ?: Status.NOTCHANGED) {
                            Status.NOTCHANGED -> {
                                // Do nothing.
                            }
                            Status.ADDED, Status.CHANGED -> {
                                // Do transform.
                                waitableExecutor.execute {
                                    doTransformFile(inputFile, outputFile, function)
                                }
                            }
                            Status.REMOVED -> {
                                // Delete
                                FileUtils.delete(outputFile)
                            }
                        }
                    }
                } else {
                    // 4.2. 非增量编译中处理文件夹逻辑
                    // Traversal fileTree (depthFirstPreOrder).
                    for (inputFile in FileUtils.getAllFiles(inputDir)) {
                        waitableExecutor.execute {
                            val outputFile = concatOutputFilePath(outputDir, inputFile)
                            if (classFilter(inputFile.name)) {
                                doTransformFile(inputFile, outputFile, function)
                            } else {
                                // Copy.
                                Files.createParentDirs(outputFile)
                                FileUtils.copyFile(inputFile, outputFile)
                            }
                        }
                    }
                }
            }
        }
        waitableExecutor.waitForTasksWithQuickFail<Any>(true)
        log("Transform end...")
    }

    /**
     * Do transform Jar.
     */
    private fun doTransformJar(inputJar: File, outputJar: File, function: ((InputStream, OutputStream) -> Unit)?) {
        // 创建父级目录来容纳 outputJar 文件。
        Files.createParentDirs(outputJar)

        //inputJar输入流
        FileInputStream(inputJar).use { fis ->
            ZipInputStream(fis).use { zis ->
                //outputJar输出流
                FileOutputStream(outputJar).use { fos ->
                    ZipOutputStream(fos).use { zos ->
                        //获取第一个文件
                        var inputEntry = zis.nextEntry
                        while (inputEntry != null && isValidZipEntryName(inputEntry)) {
                            //只处理非目录
                            if (!inputEntry.isDirectory) {
                                //调用了putNextEntry并传入一个ZipEntry对象，接下来对ZipOutputStream的write操作都会写入到这个新创建的条目（Entry）中
                                zos.putNextEntry(ZipEntry(inputEntry.name))
                                //如果符合修改条件，通过asm修改后写到outputStream中
                                if (classFilter(inputEntry.name)) {
                                    applyFunction(zis, zos, function)
                                    //log("修改jar->" + outputJar.name + "  class->"+ inputEntry.name)
                                } else {
                                    //不符合修改条件就直接复制到outputStream中
                                    zis.copyTo(zos)
                                }
                            }
                            //读取下一个文件
                            inputEntry = zis.nextEntry
                        }

                    }
                }
            }
        }
    }

    /**
     * Do transform file.
     */
    private fun doTransformFile(inputFile: File, outputFile: File, function: ((InputStream, OutputStream) -> Unit)?) {
        // Create parent directories to hold outputFile file.
        Files.createParentDirs(outputFile)
        FileInputStream(inputFile).use { fis ->
            FileOutputStream(outputFile).use { fos ->
                // Apply transform function.
                applyFunction(fis, fos, function)
                //log("修改file->" + outputFile.path)
                saveModifiedClassForCheck(outputFile)
            }
        }
    }

    private fun applyFunction(
        input: InputStream,
        output: OutputStream,
        function: ((InputStream, OutputStream) -> Unit)?
    ) {
        try {
            if (null != function) {
                function.invoke(input, output)
            } else {
                // Copy
                input.copyTo(output)
            }
        } catch (e: UncheckedIOException) {
            throw e.cause!!
        }
    }

    /**
     * 创建输出的文件
     */
    private fun concatOutputFilePath(outputDir: File, inputFile: File) = File(outputDir, inputFile.name)

    /**
     * log 打印
     */
    private fun log(logStr: String) {
        if (enableLog) {
            println(" $name - $logStr")
        }
    }

    /**
     * 检查class文件是否需要处理
     * @param fileName
     * @return
     */
    fun checkClassFile(name : String) : Boolean{
        return (name.endsWith(".class")
                && "R.class" != name
                && "BuildConfig.class" != name
                && !name.contains("R.class")
                && !name.contains("R\$")
                && !name.startsWith("META-INF/")
                && !name.startsWith("android/")
                && !name.startsWith("androidx/")
                && !name.startsWith("kotlin/")
                && !name.startsWith("kotlinx/")
                && !name.startsWith("org/intellij/")
                && !name.startsWith("org/jetbrains/")
                && !name.startsWith("com/google/")
                && !name.startsWith("io/flutter/"))
    }

    /**
     * 保存插桩后的文件到临时目录（方便查看是否插桩正确）
     */
    fun saveModifiedClassForCheck(tempClass: File) {
        tempFile?.let {
            val dir = it
            val checkJarFile = File(dir, tempClass.name.replace("/", "_"))
            if (checkJarFile.exists()) {
                checkJarFile.delete()
            }
            FileUtils.copyFile(tempClass, checkJarFile)
        }
    }
}