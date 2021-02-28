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

    public static class GetClass extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            SObject thisRef = frame.getLocalVariableTable().getThis();
            SObject jClass = thisRef.getClazz().getJClass();
            frame.pushRef(jClass);
        }
    }

    public static class HashCode extends NativeMethod {

        @Override
        public void execute(Frame frame) {

        }
    }

    public static class Clone extends NativeMethod {

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

}
