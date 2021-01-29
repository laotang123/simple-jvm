package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/28 13:31
 * @description: 无操作数的指令抽象类
 * @modified By：
 * @version: $ 1.0
 */
public abstract class NoOperandInstruction implements Instruction{
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        //nothing to do
    }
}
