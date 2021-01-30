package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:42
 * @description: int类型的相除操作
 * @modified By:
 * @version: $ 1.0
 */
public class IDiv extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        int v2 = frame.popInt();
        int v1 = frame.popInt();
        if (v2 == 0) {
            throw new ArithmeticException("/ by zero");
        }
        frame.pushInt(v1 / v2);
    }
}
