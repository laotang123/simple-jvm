package org.ljf.sjvm.instructions.control;

import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 16:26
 * @description: case 值可以编码成一个索引表。可以直接作为数组的下标。时间复杂度O(1)
 * @modified By:
 * @version: $ 1.0
 */
public class TableSwitch implements Instruction {
    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        //table switch指令操作码的后面有0-3字节的padding，保证defaultOffset在字节码中的地址是4的倍数
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.low = reader.readInt32();
        this.high = reader.readInt32();
        int jumpOffsetsCount = this.high - this.low + 1;
        this.jumpOffsets = reader.readInt32s(jumpOffsetsCount);
    }

    /**
     * 先从操作数栈中弹出一个int变量，如果在low和high给定的范围内。则从jumpOffsets表查出偏移量进行跳转
     * 否则按照defaultOffset跳转
     *
     * @param frame：方法栈帧
     */
    @Override
    public void execute(Frame frame) {
        int index = frame.popInt();
        int offset;
        if (index >= this.low && index <= this.high) {
            offset = this.jumpOffsets[index - this.low];
        } else {
            offset = this.defaultOffset;
        }

        BranchLogic.branch(frame, offset);
    }
}
