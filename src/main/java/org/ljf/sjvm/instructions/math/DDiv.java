package org.ljf.sjvm.instructions.math;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 14:42
 * @description: long类型的相除操作
 * @modified By:
 * @version: $ 1.0
 */
public class DDiv extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        double v2 = frame.popDouble();
        double v1 = frame.popDouble();

        frame.pushDouble(v1 / v2);
    }
}
