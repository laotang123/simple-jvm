package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 10:03
 * @description: 对double类型求余
 * @modified By:
 * @version: $ 1.0
 */
public class DRem extends NoOperandInstruction {

    @Override
    public void execute(Frame frame) {
        double v1 = frame.popDouble();
        double v2 = frame.popDouble();

        double result = v1 % v2;
        frame.pushDouble(result);
    }
}
