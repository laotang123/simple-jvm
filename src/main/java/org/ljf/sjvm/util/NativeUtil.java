package org.ljf.sjvm.util;

import org.ljf.sjvm.sjnative.Registry;
import org.ljf.sjvm.sjnative.java.lang.*;
import org.ljf.sjvm.sjnative.java.lang.Class;
import org.ljf.sjvm.sjnative.java.lang.Double;
import org.ljf.sjvm.sjnative.java.lang.Float;
import org.ljf.sjvm.sjnative.java.lang.Object;
import org.ljf.sjvm.sjnative.java.lang.System;
import org.ljf.sjvm.sjnative.java.lang.Throwable;
import org.ljf.sjvm.sjnative.sun.misc.VM;

/**
 * @author: ljf
 * @date: 2021/2/27 20:09
 * @description: 本地方法工具类
 * @modified By:
 * @version: $ 1.0
 */
public class NativeUtil {
    private static final String jLObject = "java/lang/Object";
    private static final String jLClass = "java/lang/Class";
    private static final String jLSystem = "java/lang/System";
    private static final String jLFloat = "java/lang/Float";
    private static final String jLDouble = "java/lang/Double";
    private static final String jLString = "java/lang/String";


    public static void registerNatives() {
        Registry.register(jLObject, "getClass", "()Ljava/lang/Class;", new Object.GetClass());
        Registry.register(jLObject, "hashCode", "()I", new Object.HashCode());
        Registry.register(jLObject, "clone", "()Ljava/lang/Object;", new Object.Clone());
        Registry.register(jLClass, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;",
                new Class.GetPrimitiveClass());
        Registry.register(jLClass, "getName0", "()Ljava/lang/String;", new Class.GetName0());
        Registry.register(jLClass, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z",
                new Class.DesiredAssertionStatus0());
        Registry.register(jLSystem, "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V",
                new System.ArrayCopy());
        Registry.register(jLSystem, "setIn0", "(Ljava/io/InputStream;)V", new System.SetInt0());
        Registry.register(jLSystem, "setOut0", "(Ljava/io/PrintStream;)V", new System.SetOut0());
        Registry.register(jLSystem, "setErr0", "(Ljava/io/PrintStream;)V", new System.SetErr0());
        Registry.register(jLFloat, "floatToRawIntBits", "(F)I", new Float.FloatToRawIntBits());
        Registry.register(jLFloat, "intBitsToFloat", "(I)F", new Float.IntBitsToFloat());
        Registry.register(jLDouble, "doubleToRawIntBits", "(D)I", new Double.DoubleToRawLongBits());
        Registry.register(jLDouble, "intBitsToFloat", "(I)F", new Double.LongBitsToDouble());
        Registry.register(jLString, "intern", "()Ljava/lang/String;", new SJString.Intern());
        Registry.register("sun/misc/VM", "initialize", "()V", new VM.Initialize());
        Registry.register("java/lang/Throwable", "fillInStackTrace", "(I)Ljava/lang/Throwable",
                new Throwable.FillInStackTrace());
    }
}
