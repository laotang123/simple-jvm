package org.ljf.sjvm.instructions.control;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/2/7 7:21
 * @description: float 类型的返回指令
 * @modified By:
 * @version: $ 1.0
 */
public class FReturn extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.getThread().popFrame();
        Frame invokeFrame = frame.getThread().peekFrame();

        float returnValue = currentFrame.popFloat();
        invokeFrame.pushFloat(returnValue);
    }
}
