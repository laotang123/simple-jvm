package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/28 13:35
 * @description: 跳转指令
 * @modified By：
 * @version: $ 1.0
 */
public class BranchInstruction implements Instruction{
    private int offset;


    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.offset = reader.readInt16();
    }

    @Override
    public void execute(Frame frame) {

    }
}
