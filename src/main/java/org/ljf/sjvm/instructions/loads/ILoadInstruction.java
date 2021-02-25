package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:04
 * @description: 加载指令从局部变量表获取变量，然后推入操作数栈顶；操作int类型变量
 * @modified By：
 * @version: $ 1.0
 */
public interface ILoadInstruction {

    default void iLoad(Frame frame, int index) {
        int value = frame.getLocalVariableTable().getInt(index);
        frame.pushInt(value);
    }
}
