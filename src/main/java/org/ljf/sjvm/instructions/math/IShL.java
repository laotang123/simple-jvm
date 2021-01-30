package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 10:20
 * @description: 有符号和无符号的左移都是一样的 ，int 类型左移指令
 * @modified By:
 * @version: $ 1.0
 */
public class IShL extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        int v2 = frame.popInt();//移位长度
        int v1 = frame.popInt();

        int s = v2 & 0x1f;//5bit表示的最大长度为31，int类型够用
        frame.pushInt(v1 << s);
    }
}
