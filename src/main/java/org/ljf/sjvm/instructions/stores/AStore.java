package org.ljf.sjvm.instructions.stores;

import org.ljf.sjvm.instructions.base.Index8Instruction;
import org.ljf.sjvm.instructions.loads.ALoadInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/29 11:08
 * @description: 存储指令把变量从操作数栈顶弹出，然后存入局部变量表；操作引用类型变量
 * @modified By：
 * @version: $ 1.0
 */
public class AStore extends Index8Instruction implements AStoreInstruction {
    @Override
    public void execute(Frame frame) {
        aStore(frame, this.index);
    }
}
