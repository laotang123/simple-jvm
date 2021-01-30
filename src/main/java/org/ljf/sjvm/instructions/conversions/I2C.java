package org.ljf.sjvm.instructions.conversions;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:18
 * @description: 将int类型转换为char
 * @modified By:
 * @version: $ 1.0
 */
public class I2C extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        int value = frame.popInt();
        frame.pushInt(value & 0xffff);//保留低16位
    }
}
