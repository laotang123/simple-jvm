package org.ljf.sjvm.sjnative.java.lang;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.sjnative.NativeMethod;
import org.ljf.sjvm.sjnative.Registry;

/**
 * @author: ljf
 * @date: 2021/2/27 10:21
 * @description: 本地方法的实现
 * @modified By:
 * @version: $ 1.0
 */
public class Object {
    private static final String jLObject = "java/lang/Object";
    private static final NativeMethod getClass = new GetClass();
    private static final NativeMethod hashCode = new HashCode();
    private static final NativeMethod clone = new Clone();

    static class GetClass extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            SObject thisRef = frame.getLocalVariableTable().getThis();
            SObject jClass = thisRef.getClazz().getJClass();
            frame.pushRef(jClass);
        }
    }

    static class HashCode extends NativeMethod {

        @Override
        public void execute(Frame frame) {

        }
    }

    static class Clone extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            SObject thisRef = frame.getLocalVariableTable().getThis();
            Class cloneable = thisRef.getClazz().getLoader().loadClass("java/lang/Cloneable");

            if (!thisRef.getClazz().isImplements(cloneable)) {
                throw new IllegalArgumentException("CloneNotSupportedException");
            }

            //TODO: cloneData待实现
        }
    }

    public Object() {
        Registry.register(jLObject, "getClass", "()Ljava/lang/Class;", getClass);
        Registry.register(jLObject, "hashCode", "()I;", hashCode);
        Registry.register(jLObject, "clone", "()Ljava/lang/Object;", clone);
    }
}
