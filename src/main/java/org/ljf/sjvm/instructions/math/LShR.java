package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 10:20
 * @description: 算术右移(有符号) ，long 类型左移指令
 * @modified By:
 * @version: $ 1.0
 */
public class LShR extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        int v2 = frame.popInt();//移位长度
        long v1 = frame.popLong();

        int s = v2 & 0x3f;//6bit表示的最大长度为63，long类型够用
        frame.pushLong(v1 >> s);
    }
}
