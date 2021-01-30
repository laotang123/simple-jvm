package org.ljf.sjvm.instructions.comparisons;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 15:33
 * @description: long 类型的比较指令
 * @modified By:
 * @version: $ 1.0
 */
public class ICmp extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        long v2 = frame.popLong();
        long v1 = frame.popLong();

        if (v1 > v2) {
            frame.pushInt(1);
        } else if (v1 == v2) {
            frame.pushInt(0);
        } else {
            frame.pushInt(-1);
        }
    }
}
