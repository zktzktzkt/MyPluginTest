package me.zkt.asm

import com.android.build.gradle.AppExtension
import com.android.utils.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 * 自定义：CustomTransformPlugin
 */
@Deprecated("不用了")
class CustomTransformPlugin2 : Plugin<Project> {

    private lateinit var tempFile: File

    override fun apply(project: Project) {
        println("Hello CustomTransformPlugin")
        initDir(project)
        //新增的代码
        // 1、获取 Android 扩展
        val androidExtension = project.extensions.getByType(AppExtension::class.java)
        // 2、注册 Transform
        androidExtension.registerTransform(MyCustomPlugin())

        val extension = project.extensions.create("myExtension", MyExtension::class.java)
        // 当前Project配置完成后，会回调project.afterEvaluate()
        project.afterEvaluate {
            val thirdPackageList = extension.thirdPackage
            print("集合：$thirdPackageList")
        }

        //AppExtension VS AndroidComponentsExtension
        //使用新方式注册
        /*val extension = project.extensions.getByType(AndroidComponentsExtension::class.java) as AndroidComponentsExtension
        extension.onVariants(extension.selector().all()){ variant ->
            variant.instrumentation.transformClassesWith(CostTimeASMFactory::class.java,InstrumentationScope.PROJECT){}
            variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS)
        }*/
    }

    fun initDir(project: Project) {
        if (!project.buildDir.exists()) {
            project.buildDir.mkdirs()
        }
        tempFile = File(project.buildDir, "asm")
        if (!tempFile.exists()) {
            tempFile.mkdir()
        }
    }

    /**
     * 保存插桩后的文件到临时目录 方便查看是否插桩正确
     */
    fun saveModifiedClassForCheck(tempClass: File) {
        val dir = tempFile
        val checkJarFile = File(dir, tempClass.getName().replace("/", "_"))
        if (checkJarFile.exists()) {
            checkJarFile.delete()
        }
        FileUtils.copyFile(tempClass, checkJarFile)
    }

}