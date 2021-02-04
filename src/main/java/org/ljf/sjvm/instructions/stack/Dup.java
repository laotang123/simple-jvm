package org.ljf.sjvm.instructions.stack;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Slot;

/**
 * @author: ljf
 * @date: 2021/1/29 13:43
 * @description: 复制栈顶的变量，只是复制的引用
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
        frame.pushSlot(new Slot(slot.num,slot.ref)); //修复原有bug，frame.pushSlot(slot)是共用同一个对象。popRef后会将ref置为null。抛出空指针异常
    }
}
