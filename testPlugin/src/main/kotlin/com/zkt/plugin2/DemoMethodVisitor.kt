package com.zkt.plugin2

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class DemoMethodVisitor(methodVisitor: MethodVisitor) : MethodVisitor(Opcodes.ASM5, methodVisitor) {
    override fun visitCode() {
        super.visitCode()
        
        mv.visitLdcInsn("TAG")
        mv.visitLdcInsn("===== This is just a test message =====")
        mv.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "android/util/Log",
            "e",
            "(Ljava/lang/String;Ljava/lang/String;)I",
            false
        )
        mv.visitInsn(Opcodes.POP)
    }
}
