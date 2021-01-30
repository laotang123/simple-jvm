package org.ljf.sjvm.instructions.comparisons;

import org.ljf.sjvm.instructions.base.BranchInstruction;
import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 15:57
 * @description: 栈顶int元素与0做对比，大于然后跳转
 * @modified By:
 * @version: $ 1.0
 */
public class IfGt extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        int value = frame.popInt();
        if (value > 0){
            BranchLogic.branch(frame,this.offset);
        }
    }
}
