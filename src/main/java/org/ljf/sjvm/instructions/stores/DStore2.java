package org.ljf.sjvm.instructions.stores;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 将栈顶元素弹出，存储到局部变量表，索引为2。double类型
 * @modified By：
 * @version: $ 1.0
 */
public class DStore2 extends NoOperandInstruction implements DStoreInstruction {
    @Override
    public void execute(Frame frame) {
        dStore(frame, 2);
    }
}
