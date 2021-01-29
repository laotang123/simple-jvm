package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 从本地变量表中加载float数据，索引为2
 * @modified By：
 * @version: $ 1.0
 */
public class FLoad2 extends NoOperandInstruction implements FLoadInstruction {
    @Override
    public void execute(Frame frame) {
        fLoad(frame, 2);
    }
}
