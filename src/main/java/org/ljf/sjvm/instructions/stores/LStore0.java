package org.ljf.sjvm.instructions.stores;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 将栈顶元素弹出，存储到局部变量表，索引为0。long类型
 * @modified By：
 * @version: $ 1.0
 */
public class LStore0 extends NoOperandInstruction implements LStoreInstruction {
    @Override
    public void execute(Frame frame) {
        lStore(frame, 0);
    }
}
