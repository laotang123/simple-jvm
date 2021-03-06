package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 11:49
 * @description: long 类型的异或操作
 * @modified By:
 * @version: $ 1.0
 */
public class LXor extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        long v2 = frame.popLong();
        long v1 = frame.popLong();
        frame.pushLong(v2 ^ v1);
    }
}
