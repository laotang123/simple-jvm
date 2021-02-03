package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/28 13:44
 * @description: 有一些指令需要访问运行时常量池，常量池索引由两字节操作数给出。
 * 把这类指令抽象成Index16Instruction
 * @modified By：
 * @version: $ 1.0
 */
public abstract class Index16Instruction implements Instruction{
    protected int index;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint16();
    }

}
