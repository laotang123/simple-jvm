package org.ljf.sjvm.instructions.stack;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Slot;

/**
 * @author: ljf
 * @date: 2021/1/29 14:01
 * @description: 交换栈顶的两个元素
 * bottom -> top
 * [...][c][b][a]
 *           \/
 *           /\
 *          V  V
 * [...][c][a][b]
 * @modified By：
 * @version: $ 1.0
 */
public class Swap extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        Slot slot1 = frame.popSLot();
        Slot slot2 = frame.popSLot();

        frame.pushSlot(slot1);
        frame.pushSlot(slot2);
    }
}
