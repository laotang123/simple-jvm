package org.ljf.sjvm.instructions.constants;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 10:07
 * @description: nop指令
 * @modified By：
 * @version: $ 1.0
 */
public class NOP extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        //nothing to do
    }
}
