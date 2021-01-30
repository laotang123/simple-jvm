package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:42
 * @description: float类型的相减操作
 * @modified By:
 * @version: $ 1.0
 */
public class FSub extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        float v2 = frame.popFloat();
        float v1 = frame.popFloat();
        frame.pushFloat(v1 - v2);
    }
}
