package org.ljf.sjvm.instructions.conversions;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:37
 * @description: long类型转int
 * @modified By:
 * @version: $ 1.0
 */
public class L2I extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        long value = frame.popLong();
        frame.pushInt((int) value);
    }
}
