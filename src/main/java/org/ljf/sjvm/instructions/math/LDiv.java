package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:42
 * @description: long类型的相除操作
 * @modified By:
 * @version: $ 1.0
 */
public class LDiv extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        long v2 = frame.popLong();
        long v1 = frame.popLong();
        if (v2 == 0) {
            throw new ArithmeticException("/ by zero");
        }
        frame.pushLong(v1 / v2);
    }
}
