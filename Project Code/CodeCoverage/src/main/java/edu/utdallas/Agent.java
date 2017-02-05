package edu.utdallas;

import java.lang.instrument.Instrumentation;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author: Twinkle Sharma
 * Create Time: 11/9/16
 */
public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("Java Agent IS Running");

        // registers the transformer
        inst.addTransformer(new MyClassFileTransform());

    }
}

