package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:04
 * @description: 加载指令从局部变量表获取变量，然后推入操作数栈顶；操作long类型变量
 * @modified By：
 * @version: $ 1.0
 */
public interface LLoadInstruction {

    default void lLoad(Frame frame, int index) {
        long value = frame.getLocalVariableTable().getLong(index);
        frame.pushLong(value);
    }
}
