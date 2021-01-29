package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.instructions.base.Index8Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 从本地变量表中加载float数据
 * @modified By：
 * @version: $ 1.0
 */
public class FLoad extends Index8Instruction implements FLoadInstruction {
    @Override
    public void execute(Frame frame) {
        fLoad(frame, this.index);
    }
}
