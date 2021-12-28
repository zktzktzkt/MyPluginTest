package com.zkt.plugin2.asm

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor

/**
 * Created by 周楷桐 on 2021/12/26.
 * Description:
 */
class TimeClassVisitor : ClassVisitor {

    private var mClassName: String = ""
    private val methodNames = arrayOf("onCreate", "onStart", "onResume", "onPause", "onStop", "onDestroy")
    private var names: ArrayList<String>

    constructor(api: Int, classVisitor: ClassVisitor?) : super(api, classVisitor) {
        val list: List<String> = methodNames.toMutableList()
        names = ArrayList(list)
    }

    override fun visit(version: Int, access: Int, name: String, signature: String?, superName: String?, interfaces: Array<String?>?) {
        this.mClassName = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(access: Int, name: String, descriptor: String, signature: String?, exceptions: Array<String>?): MethodVisitor {
        val methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)

        if (!mClassName.startsWith("android/") && !mClassName.startsWith("androidx/")) {
            println("AmsClassVisitor mClassName->$mClassName")
            println("AmsClassVisitor : change method ----> $name")
            // if (methodMatch(name)) {
            return TimeMethodVisitor(api, methodVisitor, access, name, descriptor)
            // }
        }

        return methodVisitor
    }

    /**
     * 匹配方法
     * @param name 方法名
     */
    private fun methodMatch(name: String): Boolean {
        return names.contains(name)
    }

}