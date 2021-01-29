package org.ljf.sjvm.instructions.constants;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 10:22
 * @description: 将double型的0推入操作数栈顶
 * @modified By：
 * @version: $ 1.0
 */
public class DConst0 extends NoOperandInstruction {

    @Override
    public void execute(Frame frame) {
        frame.pushDouble(0.0);
    }
}
