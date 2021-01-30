package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 10:03
 * @description: 对float类型求余
 * @modified By:
 * @version: $ 1.0
 */
public class FRem extends NoOperandInstruction {

    @Override
    public void execute(Frame frame) {
        float v1 = frame.popFloat();
        float v2 = frame.popFloat();

        float result = v1 % v2;
        frame.pushFloat(result);
    }
}
