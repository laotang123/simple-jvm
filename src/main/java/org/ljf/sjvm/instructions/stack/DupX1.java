package org.ljf.sjvm.instructions.stack;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Slot;

/**
 * @author: ljf
 * @date: 2021/1/29 13:43
 * @description: 复制栈顶的变量，插入到栈顶下两个元素
 * bottom -> top
 * [...][c][b][a]
 *           __/
 *          |
 *          V
 * [...][c][a][b][a]
 * @modified By：
 * @version: $ 1.0
 */
public class DupX1 extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        Slot slot1 = frame.popSLot();
        Slot slot2 = frame.popSLot();
        frame.pushSlot(slot1);
        frame.pushSlot(slot2);
//        frame.pushSlot(slot1);
        frame.pushSlot(new Slot(slot1.num, slot1.ref));
    }
}
