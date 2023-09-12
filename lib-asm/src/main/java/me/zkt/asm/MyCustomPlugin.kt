package me.zkt.asm

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import java.io.InputStream
import java.io.OutputStream

/**
 * function: 自定义 Transform
 */
class MyCustomPlugin : BaseCustomTransform(true), Plugin<Project> {

    override fun apply(project: Project) {
        println("Hello CustomTransformPlugin")
        initDir(project)
        //新增的代码
        // 1、获取 Android 扩展
        val androidExtension = project.extensions.getByType(AppExtension::class.java)
        // 2、注册 Transform
        androidExtension.registerTransform(this)

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

    override fun getName(): String {
        return "ZktTransform"
    }

    /**
     * 过滤只统计以 Activity.class 结尾的文件
     */
    override fun classFilter(className: String) = checkClassFile(className)

    /**
     * 用于过滤 Variant，返回 false 表示 Variant 不执行该 Transform
     */
//    @Incubating
//    override fun applyToVariant(variant: VariantInfo?): Boolean {
//        return "debug" == variant?.buildTypeName
//    }

    /**
     * 此方法可以使用 ASM 或 Javassist 进行字节码插桩
     * 目前只是一个默认实现
     */
    override fun provideFunction() = { input: InputStream, output: OutputStream ->
        val classReader = ClassReader(input)
        val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
        val cv = AsmClassVisitor(classWriter)
        classReader.accept(cv, ClassReader.EXPAND_FRAMES)
        val byteArray = classWriter.toByteArray()
        output.write(byteArray)

//        saveModifiedClassForCheck(inputFile);
    }

}