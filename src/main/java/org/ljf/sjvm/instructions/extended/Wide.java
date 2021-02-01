package org.ljf.sjvm.instructions.extended;

import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.instructions.loads.*;
import org.ljf.sjvm.instructions.math.IInc;
import org.ljf.sjvm.instructions.stores.*;
import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/1/30 17:14
 * @description: 扩展指令，用于局部变量表的大小超过uint8，即256个表项
 * @modified By:
 * @version: $ 1.0
 */
public class Wide implements Instruction {
    private Instruction modifiedInstruction;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        short opCode = reader.readUint8();
        switch (opCode) {
            case 0x15:
                ILoad iLoad = new ILoad();
                iLoad.setIndex(reader.readUint16());
                this.modifiedInstruction = iLoad;
                break;
            case 0x16:
                LLoad lLoad = new LLoad();
                lLoad.setIndex(reader.readUint16());
                this.modifiedInstruction = lLoad;
                break;
            case 0x17:
                FLoad fLoad = new FLoad();
                fLoad.setIndex(reader.readUint16());
                this.modifiedInstruction = fLoad;
                break;
            case 0x18:
                DLoad dLoad = new DLoad();
                dLoad.setIndex(reader.readUint16());
                this.modifiedInstruction = dLoad;
                break;
            case 0x19:
                ALoad aLoad = new ALoad();
                aLoad.setIndex(reader.readUint16());
                this.modifiedInstruction = aLoad;
                break;

            case 0x36:
                IStore iStore = new IStore();
                iStore.setIndex(reader.readUint16());
                this.modifiedInstruction = iStore;
                break;

            case 0x37:
                LStore lStore = new LStore();
                lStore.setIndex(reader.readUint16());
                this.modifiedInstruction = lStore;
                break;

            case 0x38:
                FStore fStore = new FStore();
                fStore.setIndex(reader.readUint16());
                this.modifiedInstruction = fStore;
                break;

            case 0x39:
                DStore dStore = new DStore();
                dStore.setIndex(reader.readUint16());
                this.modifiedInstruction = dStore;
                break;

            case 0x3a:
                AStore aStore = new AStore();
                aStore.setIndex(reader.readUint16());
                this.modifiedInstruction = aStore;
                break;
            case 0x84:
                IInc iInc = new IInc();
                iInc.setIndex(reader.readUint16());
                iInc.setConstValue(reader.readInt16());
                this.modifiedInstruction = iInc;
                break;
            case 0xa9:
                throw new IllegalArgumentException("Unsupported opcode: 0xa9!");
        }
    }

    @Override
    public void execute(Frame frame) {
        this.modifiedInstruction.execute(frame);
    }
}
