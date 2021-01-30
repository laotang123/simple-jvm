package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 10:03
 * @description: 对int类型求余
 * @modified By:
 * @version: $ 1.0
 */
public class IRem extends NoOperandInstruction {

    @Override
    public void execute(Frame frame) {
        int v1 = frame.popInt();
        int v2 = frame.popInt();

        if (v2 == 0) {
            throw new ArithmeticException("/ by zero");
        }

        int result = v1 % v2;
        frame.pushInt(result);
    }
}
