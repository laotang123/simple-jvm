package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/28 13:27
 * @description: 指令接口
 * @modified By：
 * @version: $ 1.0
 */
public interface Instruction {
    /**
     * 从字节码中提取操作数
     * @param reader：字节码读取器
     */
    void fetchOperands(ByteCodeReader reader);

    /**
     * 执行指令逻辑
     * @param frame：方法栈帧
     */
    void execute(Frame frame);
}
