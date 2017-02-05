package edu.utdallas;

import edu.utdallas.util.Helper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by twinklesharma on 11/16/16.
 */
class MyClassFileTransform implements ClassFileTransformer {


    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (Helper.isClassUnderTest(className)){
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassTransformVisitor ca = new ClassTransformVisitor(cw);
            cr.accept(ca, 0);
            return cw.toByteArray();
        }
        return classfileBuffer;
    }
}
