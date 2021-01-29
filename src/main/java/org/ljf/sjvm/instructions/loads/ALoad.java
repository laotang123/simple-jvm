package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.instructions.base.Index8Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 从本地变量表中加载引用
 * @modified By：
 * @version: $ 1.0
 */
public class ALoad extends Index8Instruction implements ALoadInstruction {
    @Override
    public void execute(Frame frame) {
        aLoad(frame, this.index);
    }
}
