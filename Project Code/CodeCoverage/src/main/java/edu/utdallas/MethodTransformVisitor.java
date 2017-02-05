package edu.utdallas;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by twinklesharma on 11/16/16.
 */
class MethodTransformVisitor extends MethodVisitor implements Opcodes {
    String className;
    public MethodTransformVisitor(final MethodVisitor mv, String classname) {

        super(ASM5, mv);
        this.className=classname;

    }


    @Override
    public void visitLineNumber(int line, Label start) {

        CodeCoverageAPI.addLine(className,line);  //asm
        //jvm
        mv.visitLdcInsn(className);
        mv.visitLdcInsn(line);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKESTATIC, "edu/utdallas/CodeCoverageAPI", "markLineExecuted", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        super.visitLineNumber(line, start);
    }

    @Override
    public void visitEnd() {
        // Send all the line number (Set) to a global static class

        super.visitEnd();
    }
}
