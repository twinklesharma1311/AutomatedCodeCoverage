package edu.utdallas;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author: Twinkle Sharma
 * Create Time: 11/9/16
 */
class ClassTransformVisitor extends ClassVisitor implements Opcodes {
    String classname;
    public ClassTransformVisitor(final ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visit(int i, int i1, String className, String s1, String s2, String[] strings) {
        super.visit(i, i1, className, s1, s2, strings);
        this.classname=className;

    }

    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        return mv == null ? null : new MethodTransformVisitor(mv,classname);
    }
}
