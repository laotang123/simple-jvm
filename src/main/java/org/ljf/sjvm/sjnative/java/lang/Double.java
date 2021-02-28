package org.ljf.sjvm.sjnative.java.lang;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.sjnative.NativeMethod;

/**
 * @author: ljf
 * @date: 2021/2/28 8:05
 * @description: double的本地方法
 * @modified By:
 * @version: $ 1.0
 */
public class Double {
    //public static native int doubleToRawLongBits(double value)
    public static class DoubleToRawLongBits extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            double value = frame.getLocalVariableTable().getDouble(0);
            long bits = java.lang.Double.doubleToLongBits(value);
            frame.pushLong(bits);
        }
    }


    public static class LongBitsToDouble extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            long bits = frame.getLocalVariableTable().getLong(0);
            double value = java.lang.Double.longBitsToDouble(bits);
            frame.pushDouble(value);
        }
    }
}
