package org.ljf.sjvm.instructions.conversions;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:09
 * @description: 将float类型的变量强转为double
 * @modified By:
 * @version: $ 1.0
 */
public class F2D extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        float value = frame.popFloat();
        frame.pushDouble(value);
    }
}
