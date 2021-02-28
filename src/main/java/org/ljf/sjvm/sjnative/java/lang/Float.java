package org.ljf.sjvm.sjnative.java.lang;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.sjnative.NativeMethod;

/**
 * @author: ljf
 * @date: 2021/2/28 8:05
 * @description: float的本地方法
 * @modified By:
 * @version: $ 1.0
 */
public class Float {
    //public static native int floatToRawIntBits(float value)
    public static class FloatToRawIntBits extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            float value = frame.getLocalVariableTable().getFloat(0);
            int bits = java.lang.Float.floatToIntBits(value);
            frame.pushInt(bits);
        }
    }


    public static class IntBitsToFloat extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            int bits = frame.getLocalVariableTable().getInt(0);
            float value = java.lang.Float.intBitsToFloat(bits);
            frame.pushFloat(value);
        }
    }
}
