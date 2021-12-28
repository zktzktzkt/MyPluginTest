package com.zkt.plugin2.asm

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * Created by 周楷桐 on 2021/12/26.
 * Description:
 */
class TimeMethodVisitor constructor(api: Int, methodVisitor: MethodVisitor?, access: Int, val mMethodName: String?, descriptor: String?)
    : AdviceAdapter(api, methodVisitor, access, mMethodName, descriptor) {


    override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
        super.visitFieldInsn(opcode, owner, name, descriptor)
    }

    /**
     * @param owner 类的全路径 com/lpc/testgradle/TestActivity
     * @param name 方法名 setContentView
     * @param descriptor 参数和返回值 (I)V
     */
    override fun visitMethodInsn(opcodeAndSource: Int, owner: String, name: String, descriptor: String?, isInterface: Boolean) {
        println("TimeMethodVisitor visitMethodInsn owner->$owner name->$name descriptor->$descriptor")
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface)
    }


    override fun onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
        mv.visitVarInsn(LSTORE, 1)
        super.onMethodEnter()
    }

    override fun onMethodExit(opcode: Int) {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
        mv.visitVarInsn(LSTORE, 3)
        mv.visitLdcInsn("zkt")
        mv.visitTypeInsn(NEW, "java/lang/StringBuilder")
        mv.visitInsn(DUP)
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false)
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false)
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitLdcInsn(" ----> onPause execute cost ")
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitVarInsn(LLOAD, 3)
        mv.visitVarInsn(LLOAD, 1)
        mv.visitInsn(LSUB)
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false)
        mv.visitLdcInsn("ms")
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false)
        mv.visitInsn(POP)
        super.onMethodExit(opcode)
    }
}