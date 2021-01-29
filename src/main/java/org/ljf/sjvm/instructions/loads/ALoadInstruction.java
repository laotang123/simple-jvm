package org.ljf.sjvm.instructions.loads;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Object;

/**
 * @author: ljf
 * @date: 2021/1/29 11:04
 * @description: 加载指令从局部变量表获取变量，然后推入操作数栈顶；操作引用类型变量
 * @modified By：
 * @version: $ 1.0
 */
public interface ALoadInstruction {

    default void aLoad(Frame frame, int index) {
        Object ref = frame.getLocalVariableTable().getRef(index);
        frame.pushRef(ref);
    }
}
