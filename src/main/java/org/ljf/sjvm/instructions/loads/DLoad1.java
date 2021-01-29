package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.instructions.base.Index8Instruction;
import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 从本地变量表中加载double数据，索引为1
 * @modified By：
 * @version: $ 1.0
 */
public class DLoad1 extends NoOperandInstruction implements DLoadInstruction {
    @Override
    public void execute(Frame frame) {
        dLoad(frame, 1);
    }
}
