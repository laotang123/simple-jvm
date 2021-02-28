package org.ljf.sjvm.sjnative.java.lang;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.rtda.heap.StringPool;
import org.ljf.sjvm.sjnative.NativeMethod;

/**
 * @author: ljf
 * @date: 2021/2/28 8:20
 * @description: string的本地方法
 * @modified By:
 * @version: $ 1.0
 */
public class SJString {
    public static class Intern extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            SObject thisRef = frame.getLocalVariableTable().getThis();
            SObject interned = StringPool.internString(thisRef);
            frame.getOperandStack().pushRef(interned);
        }
    }
}
