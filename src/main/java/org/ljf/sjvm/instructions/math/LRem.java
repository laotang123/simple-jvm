package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 10:03
 * @description: 对long类型求余
 * @modified By:
 * @version: $ 1.0
 */
public class LRem extends NoOperandInstruction {

    @Override
    public void execute(Frame frame) {
        long v1 = frame.popLong();
        long v2 = frame.popLong();

        if (v2 == 0) {
            throw new ArithmeticException("/ by zero");
        }

        long result = v1 % v2;
        frame.pushLong(result);
    }
}
