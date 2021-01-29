package org.ljf.sjvm.instructions.stack;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Slot;

/**
 * @author: ljf
 * @date: 2021/1/29 13:43
 * @description: 复制栈顶的两个元素，插入到下四个元素中
 * bottom -> top
 * [...][d][c][b][a]
 *        ____/ __/
 *       |   __/
 *       V  V
 * [...][b][a][d][c][b][a]
 * @modified By：
 * @version: $ 1.0
 */
public class Dup2X2 extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        Slot slot1 = frame.popSLot();
        Slot slot2 = frame.popSLot();
        Slot slot3 = frame.popSLot();
        Slot slot4 = frame.popSLot();
        frame.pushSlot(slot2);
        frame.pushSlot(slot1);
        frame.pushSlot(slot4);
        frame.pushSlot(slot3);
        frame.pushSlot(slot2);
        frame.pushSlot(slot1);
    }
}
