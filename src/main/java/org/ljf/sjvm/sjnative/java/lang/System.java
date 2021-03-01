package org.ljf.sjvm.sjnative.java.lang;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.LocalVariableTable;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassArray;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.rtda.heap.SObjectArray;
import org.ljf.sjvm.sjnative.NativeMethod;

/**
 * @author: ljf
 * @date: 2021/2/28 6:57
 * @description: 实现system的本地方法
 * @modified By:
 * @version: $ 1.0
 */
public class System {
    // public static native void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
    // (Ljava/lang/Object;ILjava/lang/Object;II)V
    public static class ArrayCopy extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            LocalVariableTable vars = frame.getLocalVariableTable();
            SObject src = vars.getRef(0);
            int srcPos = vars.getInt(1);
            SObject dest = vars.getRef(2);
            int destPos = vars.getInt(3);
            int length = vars.getInt(4);

            if (src == null || dest == null) {
                throw new NullPointerException();
            }

            if (!checkArrayCopy(src, dest)) {
                throw new ArrayStoreException();
            }

            if (srcPos < 0 || destPos < 0 || length < 0
                    || srcPos + length > ((SObjectArray) src).arrayLength()
                    || destPos + length > ((SObjectArray) dest).arrayLength()) {
                throw new IndexOutOfBoundsException();
            }
            SObjectArray.arrayCopy(src, dest, srcPos, destPos, length);
        }

        private boolean checkArrayCopy(SObject src, SObject dest) {
            ClassArray srcClass = (ClassArray) src.getClazz();
            ClassArray destClass = (ClassArray) dest.getClazz();

            if (!srcClass.isArray() || !destClass.isArray()) {
                return false;
            }

            if (srcClass.componentClass().isPrimitive() ||
                    destClass.componentClass().isPrimitive()) {
                return srcClass == destClass;
            }
            return true;
        }
    }

    // private static native void setIn0(InputStream in);
    // (Ljava/io/InputStream;)V
    public static class SetInt0 extends NativeMethod {
        @Override
        public void execute(Frame frame) {
            LocalVariableTable vars = frame.getLocalVariableTable();
            SObject in = vars.getRef(0);

            Class sysClass = frame.getMethod().getClazz();
            sysClass.setRefVar("in","Ljava/io/InputStream;", in);
        }
    }

    public static class SetErr0 extends NativeMethod {
        @Override
        public void execute(Frame frame) {
            LocalVariableTable vars = frame.getLocalVariableTable();
            SObject err = vars.getRef(0);

            Class sysClass = frame.getMethod().getClazz();
            sysClass.setRefVar("err","Ljava/io/InputStream;", err);
        }
    }

    public static class SetOut0 extends NativeMethod {
        @Override
        public void execute(Frame frame) {
            LocalVariableTable vars = frame.getLocalVariableTable();
            SObject out = vars.getRef(0);

            Class sysClass = frame.getMethod().getClazz();
            sysClass.setRefVar("out","Ljava/io/InputStream;", out);
        }
    }


}
