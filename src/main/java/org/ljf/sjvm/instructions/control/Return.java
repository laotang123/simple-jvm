package org.ljf.sjvm.instructions.control;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/2/7 7:19
 * @description: return 指令 void类型只需弹出当前栈中的栈帧即可
 * @modified By:
 * @version: $ 1.0
 */
public class Return extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}
