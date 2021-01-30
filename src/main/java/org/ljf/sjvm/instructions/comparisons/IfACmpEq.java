package org.ljf.sjvm.instructions.comparisons;

import org.ljf.sjvm.instructions.base.BranchInstruction;
import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 16:09
 * @description: 弹出栈顶的两个引用类型，如果相等就进行跳转
 * @modified By:
 * @version: $ 1.0
 */
public class IfACmpEq extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        Object ref2 = frame.popRef();
        Object ref1 = frame.popRef();
        if (ref1 == ref2){
            BranchLogic.branch(frame,this.offset);
        }
    }
}
