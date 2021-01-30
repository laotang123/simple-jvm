package org.ljf.sjvm.instructions.control;

import org.ljf.sjvm.instructions.base.BranchLogic;
import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 16:40
 * @description: case 值不能编码成下标，需要遍历key-value的数组。时间复杂度O(n)
 * @modified By:
 * @version: $ 1.0
 */
public class LookupSwitch implements Instruction {
    private int defaultOffset;
    private int nPairs;
    private int[] matchOffsets;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.nPairs = reader.readInt32();
        this.matchOffsets = reader.readInt32s(this.nPairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.popInt();

        for (int i = 0; i < this.nPairs * 2; i += 2) {//只遍历key
            if (this.matchOffsets[i] == key) {
                int offset = this.matchOffsets[i + 1];
                BranchLogic.branch(frame, offset);
                break;
            }
        }
        BranchLogic.branch(frame, defaultOffset);
    }
}
