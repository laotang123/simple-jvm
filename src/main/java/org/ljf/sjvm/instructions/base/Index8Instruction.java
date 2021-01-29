package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/28 13:39
 * @description: 存储和加载类指令需要根据索引存取局部变量表，索引由单字节操作数（256）给出
 * 把这里指令抽象成Index8Instruction
 * @modified By：
 * @version: $ 1.0
 */
public abstract class Index8Instruction implements Instruction{
    protected short index;


    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint8();
    }

}
