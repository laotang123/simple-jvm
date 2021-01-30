package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 13:58
 * @description: iinc指令给局部变量表中的int变量增加常量值，局部变量表索引和常量值都有指令的操作数提供。
 * @modified By:
 * @version: $ 1.0
 */
public class IInc implements Instruction {
    private int index;
    private int constValue;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setConstValue(int constValue) {
        this.constValue = constValue;
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint8();
        this.constValue = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        int value = frame.getInt(this.index);
        value += this.constValue;
        frame.setInt(this.index,value);
    }
}
