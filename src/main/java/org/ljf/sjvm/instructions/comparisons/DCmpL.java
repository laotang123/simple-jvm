package org.ljf.sjvm.instructions.comparisons;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 15:37
 * @description: double类型的比较指令，两个数中有nan值。压入栈中为-1
 * @modified By:
 * @version: $ 1.0
 */
public class DCmpL extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        double v2 = frame.popDouble();
        double v1 = frame.popDouble();

        if (v1 > v2) {
            frame.pushInt(1);
        } else if (v1 == v2) {
            frame.pushInt(0);
        } else if (v1 < v2) {
            frame.pushInt(-1);
        } else {
            frame.pushInt(-1);
        }
    }
}
