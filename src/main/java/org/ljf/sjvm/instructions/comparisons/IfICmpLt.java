package org.ljf.sjvm.instructions.comparisons;

import org.ljf.sjvm.instructions.base.BranchInstruction;
import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 15:55
 * @description: 弹出栈顶的两个元素，如果小于就进行跳转
 * @modified By:
 * @version: $ 1.0
 */
public class IfICmpLt extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        int v2 = frame.popInt();
        int v1 = frame.popInt();
        if (v1 < v2) {
            BranchLogic.branch(frame, this.offset);
        }
    }
}
