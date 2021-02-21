package org.ljf.sjvm.instructions.extended;

import org.ljf.sjvm.instructions.base.BranchInstruction;
import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.SObject;

/**
 * @author: ljf
 * @date: 2021/1/30 17:31
 * @description: 根据栈顶的元素如果不是null，则进行跳转
 * @modified By:
 * @version: $ 1.0
 */
public class IfNonNull extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        SObject ref = frame.popRef();
        if (ref != null){
            BranchLogic.branch(frame,this.offset);
        }
    }
}
