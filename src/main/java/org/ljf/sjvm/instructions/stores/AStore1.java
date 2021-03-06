package org.ljf.sjvm.instructions.stores;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 将栈顶元素弹出，存储到局部变量表，索引为1。
 * @modified By：
 * @version: $ 1.0
 */
public class AStore1 extends NoOperandInstruction implements AStoreInstruction {
    @Override
    public void execute(Frame frame) {
        aStore(frame, 1);
    }
}
