package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 15:46
 * @description: 跳转指令
 * @modified By:
 * @version: $ 1.0
 */
public class BranchLogic {
    //完成条件指令跳转
    public static void branch(Frame frame, int offset) {
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPc(nextPc);
    }
}
