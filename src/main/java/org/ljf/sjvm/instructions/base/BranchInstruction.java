package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/28 13:35
 * @description: 跳转指令
 * @modified By：
 * @version: $ 1.0
 */
public abstract class BranchInstruction implements Instruction{
    protected int offset;


    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.offset = reader.readInt16();
    }
}
