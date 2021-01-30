package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 11:49
 * @description: int 类型的与操作
 * @modified By:
 * @version: $ 1.0
 */
public class IAnd extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        int v2 = frame.popInt();
        int v1 = frame.popInt();
        frame.pushInt(v2 & v1);
    }
}
