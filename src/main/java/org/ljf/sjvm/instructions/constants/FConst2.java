package org.ljf.sjvm.instructions.constants;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 10:22
 * @description: 将float型的1推入操作数栈顶
 * @modified By：
 * @version: $ 1.0
 */
public class FConst2 extends NoOperandInstruction {

    @Override
    public void execute(Frame frame) {
        frame.pushFloat(2.0f);
    }
}
