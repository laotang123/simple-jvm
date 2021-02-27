package org.ljf.sjvm.sjnative.java.lang;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.ClassLoader;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.rtda.heap.StringPool;
import org.ljf.sjvm.sjnative.NativeMethod;
import org.ljf.sjvm.sjnative.Registry;

/**
 * @author: ljf
 * @date: 2021/2/27 10:43
 * @description: class本地方法
 * @modified By:
 * @version: $ 1.0
 */
public class Class {
    private static final String jLClass = "java/lang/Class";
    private static final NativeMethod getPrimitiveClass = new GetPrimitiveClass();
    private static final NativeMethod getName0 = new GetName0();
    private static final NativeMethod desiredAssertionStatus0 = new DesiredAssertionStatus0();

    static class GetPrimitiveClass extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            SObject nameObj = frame.getLocalVariableTable().getRef(0);
            String name = StringPool.jString(nameObj);

            ClassLoader loader = frame.getMethod().getClazz().getLoader();
            SObject jClass = loader.loadClass(name).getJClass();
            frame.pushRef(jClass);
        }
    }


    static class GetName0 extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            SObject thisRef = frame.getLocalVariableTable().getThis();
            org.ljf.sjvm.rtda.heap.Class extraClazz = (org.ljf.sjvm.rtda.heap.Class) thisRef.getExtra();

            String name = extraClazz.javaName();
            SObject nameObj = StringPool.stringObject(extraClazz.getLoader(), name);
            frame.pushRef(nameObj);
        }
    }

    static class DesiredAssertionStatus0 extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            //todo
            frame.pushBoolean(false);
        }
    }


    public Class() {
        Registry.register(jLClass, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", getPrimitiveClass);
        Registry.register(jLClass, "getName0", "()Ljava/lang/String;", getName0);
        Registry.register(jLClass, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", desiredAssertionStatus0);

    }
}
