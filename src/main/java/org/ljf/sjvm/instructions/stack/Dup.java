package org.ljf.sjvm.instructions.stack;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Slot;

/**
 * @author: ljf
 * @date: 2021/1/29 13:43
 * @description: 复制栈顶的变量
 * bottom -> top
 * [...][c][b][a]
 *              \_
 *                |
 *                V
 * [...][c][b][a][a]
 * @modified By：
 * @version: $ 1.0
 */
public class Dup extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        Slot slot = frame.popSLot();
        frame.pushSlot(slot);
        frame.pushSlot(slot);
    }
}
