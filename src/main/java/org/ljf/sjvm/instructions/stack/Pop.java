package org.ljf.sjvm.instructions.stack;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 13:40
 * @description: 将栈顶变量弹出
 * bottom -> top
 * [...][c][b][a]
 *             |
 *             V
 * [...][c][b]
 * @modified By：
 * @version: $ 1.0
 */
public class Pop extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        frame.popSLot();
    }
}
