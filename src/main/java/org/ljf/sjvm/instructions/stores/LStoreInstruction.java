package org.ljf.sjvm.instructions.stores;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:44
 * @description: 存储指令把变量从操作数栈顶弹出，然后存入局部变量表；操作long类型变量
 * @modified By：
 * @version: $ 1.0
 */
public interface LStoreInstruction {
    default void lStore(Frame frame, int index) {

        long value = frame.popLong();
        frame.setLong(index, value);
    }
}
