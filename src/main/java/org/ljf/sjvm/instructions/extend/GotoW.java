package org.ljf.sjvm.instructions.extend;

import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 17:34
 * @description: 与goto的区别就是索引由原来的2字节变为4字节
 * @modified By:
 * @version: $ 1.0
 */
public class GotoW implements Instruction {
    private int offset;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.offset = reader.readInt32();
    }

    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame, offset);
    }
}
