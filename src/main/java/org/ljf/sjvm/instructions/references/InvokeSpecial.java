package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/2/4 7:30
 * @description: 在创建类实例时，编译器会在new指令的后面加入invoke_special指令来调用构造函数初始化对象
 * 空的实现
 * @modified By:
 * @version: $ 1.0
 */
public class InvokeSpecial extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        frame.popRef();
    }
}
