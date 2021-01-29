package org.ljf.sjvm.instructions.constants;

import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 10:37
 * @description: 从操作数中获取一个byte，扩展成int型，然后推入栈顶
 * @modified By：
 * @version: $ 1.0
 */
public class BIPush implements Instruction {
    private byte value;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.value = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        frame.pushInt(value);
    }
}
