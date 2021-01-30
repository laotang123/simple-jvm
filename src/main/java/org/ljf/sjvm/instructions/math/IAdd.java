package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:42
 * @description: int类型的相加操作
 * @modified By:
 * @version: $ 1.0
 */
public class IAdd extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        int v1 = frame.popInt();
        int v2 = frame.popInt();
        frame.pushInt(v1 + v2);
    }
}
