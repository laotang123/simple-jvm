package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 从本地变量表中加载long数据，索引为0
 * @modified By：
 * @version: $ 1.0
 */
public class LLoad0 extends NoOperandInstruction implements LLoadInstruction {
    @Override
    public void execute(Frame frame) {
        lLoad(frame, 0);
    }
}
