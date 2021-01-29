package org.ljf.sjvm.instructions.constants;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 10:24
 * @description: 将int型0推入操作数栈顶
 * @modified By：
 * @version: $ 1.0
 */
public class IConst0 extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        frame.pushInt(0);
    }
}
