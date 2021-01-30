package org.ljf.sjvm.instructions.control;

import org.ljf.sjvm.instructions.base.BranchInstruction;
import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 16:22
 * @description: 无条件跳转
 * @modified By:
 * @version: $ 1.0
 */
public class Goto extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame, this.offset);
    }
}
