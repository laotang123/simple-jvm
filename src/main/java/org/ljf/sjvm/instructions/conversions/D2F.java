package org.ljf.sjvm.instructions.conversions;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:09
 * @description: 将double类型的变量强转为float
 * @modified By:
 * @version: $ 1.0
 */
public class D2F extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        double value = frame.popDouble();
        frame.pushFloat((float) value);
    }
}
