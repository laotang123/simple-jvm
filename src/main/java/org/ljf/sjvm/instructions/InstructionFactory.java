package org.ljf.sjvm.instructions;

import com.sun.org.apache.bcel.internal.generic.LOR;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.instructions.comparisons.*;
import org.ljf.sjvm.instructions.constants.*;
import org.ljf.sjvm.instructions.conversions.*;
import org.ljf.sjvm.instructions.loads.*;
import org.ljf.sjvm.instructions.math.*;
import org.ljf.sjvm.instructions.stack.*;
import org.ljf.sjvm.instructions.stores.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2021/1/30 19:42
 * @description: 创建instruction工厂
 * @modified By:
 * @version: $ 1.0
 */
public class InstructionFactory {
    private static final Map<String, Instruction> instructionMap = new HashMap<>();

    static {
        instructionMap.put("nop", new NOP());
        instructionMap.put("aConstNull", new AConstNull());
        instructionMap.put("iConstM1", new IConstM1());
        instructionMap.put("iConst0", new IConst0());
        instructionMap.put("iConst1", new IConst1());
        instructionMap.put("iConst2", new IConst2());
        instructionMap.put("iConst3", new IConst3());
        instructionMap.put("iConst4", new IConst4());
        instructionMap.put("iConst5", new IConst5());
        instructionMap.put("lConst0", new LConst0());
        instructionMap.put("lConst1", new LConst1());
        instructionMap.put("fConst0", new FConst0());
        instructionMap.put("fConst1", new FConst1());
        instructionMap.put("fConst2", new FConst2());
        instructionMap.put("dConst0", new DConst0());
        instructionMap.put("dConst1", new DConst1());
        instructionMap.put("iLoad0", new ILoad0());
        instructionMap.put("iLoad1", new ILoad1());
        instructionMap.put("iLoad2", new ILoad2());
        instructionMap.put("iLoad3", new ILoad3());
        instructionMap.put("lLoad0", new LLoad0());
        instructionMap.put("lLoad1", new LLoad1());
        instructionMap.put("lLoad2", new LLoad2());
        instructionMap.put("lLoad3", new LLoad3());
        instructionMap.put("fLoad0", new FLoad0());
        instructionMap.put("fLoad1", new FLoad1());
        instructionMap.put("fLoad2", new FLoad2());
        instructionMap.put("fLoad3", new FLoad3());
        instructionMap.put("dLoad0", new DLoad0());
        instructionMap.put("dLoad1", new DLoad1());
        instructionMap.put("dLoad2", new DLoad2());
        instructionMap.put("dLoad3", new DLoad3());
        instructionMap.put("aLoad0", new ALoad0());
        instructionMap.put("aLoad1", new ALoad1());
        instructionMap.put("aLoad2", new ALoad2());
        instructionMap.put("aLoad3", new ALoad3());
        // iaload      = &IALOAD{}
        // laload      = &LALOAD{}
        // faload      = &FALOAD{}
        // daload      = &DALOAD{}
        // aaload      = &AALOAD{}
        // baload      = &BALOAD{}
        // caload      = &CALOAD{}
        // saload      = &SALOAD{}
        instructionMap.put("iStore0", new IStore0());
        instructionMap.put("iStore1", new IStore1());
        instructionMap.put("iStore2", new IStore2());
        instructionMap.put("iStore3", new IStore3());
        instructionMap.put("lStore0", new LStore0());
        instructionMap.put("lStore1", new LStore1());
        instructionMap.put("lStore2", new LStore2());
        instructionMap.put("lStore3", new LStore3());
        instructionMap.put("fStore0", new FStore0());
        instructionMap.put("fStore1", new FStore1());
        instructionMap.put("fStore2", new FStore2());
        instructionMap.put("fStore3", new FStore3());
        instructionMap.put("dStore0", new DStore0());
        instructionMap.put("dStore1", new DStore1());
        instructionMap.put("dStore2", new DStore2());
        instructionMap.put("dStore3", new DStore3());
        instructionMap.put("aStore0", new AStore0());
        instructionMap.put("aStore1", new AStore1());
        instructionMap.put("aStore2", new AStore2());
        instructionMap.put("aStore3", new AStore3());
        // iastore  = &IASTORE{}
        // lastore  = &LASTORE{}
        // fastore  = &FASTORE{}
        // dastore  = &DASTORE{}
        // aastore  = &AASTORE{}
        // bastore  = &BASTORE{}
        // castore  = &CASTORE{}
        // sastore  = &SASTORE{}
        instructionMap.put("pop", new Pop());
        instructionMap.put("pop2", new Pop2());
        instructionMap.put("dup", new Dup());
        instructionMap.put("dupX1", new DupX1());
        instructionMap.put("dupX2", new DupX2());
        instructionMap.put("dup2", new Dup2());
        instructionMap.put("dup2X1", new Dup2X1());
        instructionMap.put("dup2X2", new Dup2X2());
        instructionMap.put("swap", new Swap());
        instructionMap.put("iAdd", new IAdd());
        instructionMap.put("lAdd", new LAdd());
        instructionMap.put("fAdd", new FAdd());
        instructionMap.put("dAdd", new DAdd());
        instructionMap.put("iSub", new ISub());
        instructionMap.put("lSub", new LSub());
        instructionMap.put("fSub", new FSub());
        instructionMap.put("dSub", new DSub());
        instructionMap.put("iMul", new IMul());
        instructionMap.put("lMul", new LMul());
        instructionMap.put("fMul", new FMul());
        instructionMap.put("dMul", new DMul());
        instructionMap.put("iDiv", new IDiv());
        instructionMap.put("lDiv", new LDiv());
        instructionMap.put("fDiv", new FDiv());
        instructionMap.put("dDiv", new DDiv());
        instructionMap.put("iRem", new IRem());
        instructionMap.put("lRem", new LRem());
        instructionMap.put("fRem", new FRem());
        instructionMap.put("dRem", new DRem());
        instructionMap.put("iShl", new IShL());
        instructionMap.put("iShr", new IShR());
        instructionMap.put("lShl", new LShL());
        instructionMap.put("lShr", new LShR());
        instructionMap.put("iuShr", new IUShR());
        instructionMap.put("luShr", new LUShR());
        instructionMap.put("iAnd", new IAnd());
        instructionMap.put("iOr", new IOr());
        instructionMap.put("lOr", new LOr());
        instructionMap.put("iXor", new IXor());
        instructionMap.put("lXor", new LXor());
        instructionMap.put("lAnd", new LAnd());
        instructionMap.put("i2l", new I2L());
        instructionMap.put("i2f", new I2F());
        instructionMap.put("i2d", new I2D());
        instructionMap.put("l2i", new L2I());
        instructionMap.put("l2f", new L2F());
        instructionMap.put("l2d", new L2D());
        instructionMap.put("f2i", new F2I());
        instructionMap.put("f2l", new F2L());
        instructionMap.put("f2d", new F2D());
        instructionMap.put("d2i", new D2I());
        instructionMap.put("d2l", new D2L());
        instructionMap.put("d2f", new D2F());
        instructionMap.put("i2b", new I2B());
        instructionMap.put("i2c", new I2C());
        instructionMap.put("i2s", new I2S());
        instructionMap.put("lCmp", new LCmp());
        instructionMap.put("fCmpL", new FCmpL());
        instructionMap.put("fCmpG", new FCmpG());
        instructionMap.put("dCmpL", new DCmpL());
        instructionMap.put("dCmpG", new DCmpG());

//        ineg = &INEG { }
//        lneg = &LNEG { }
//        fneg = &FNEG { }
//        dneg = &DNEG { }
        // ireturn = &IRETURN{}
        // lreturn = &LRETURN{}
        // freturn = &FRETURN{}
        // dreturn = &DRETURN{}
        // areturn = &ARETURN{}
        // _return = &RETURN{}
        // arraylength   = &ARRAY_LENGTH{}
        // athrow        = &ATHROW{}
        // monitorenter  = &MONITOR_ENTER{}
        // monitorexit   = &MONITOR_EXIT{}
        // invoke_native = &INVOKE_NATIVE{}

    }

    //map结构，单例
    //TODO: 根据opCode的最终使用，考虑改为byte类型
    public static Instruction newInstruction(short opCode) {
        switch (opCode) {
            case 0x00:
                return instructionMap.get("nop");
            case 0x01:
                return instructionMap.get("aConstNull");
            case 0x02:
                return instructionMap.get("iConstM1");
            case 0x03:
                return instructionMap.get("iConst0");
            case 0x04:
                return instructionMap.get("iConst1");
            case 0x05:
                return instructionMap.get("iConst2");
            case 0x06:
                return instructionMap.get("iConst3");
            case 0x07:
                return instructionMap.get("iConst4");
            case 0x08:
                return instructionMap.get("iConst5");
            case 0x09:
                return instructionMap.get("lConst0");
            case 0x0a:
                return instructionMap.get("lConst1");
            case 0x0b:
                return instructionMap.get("fConst0");
            case 0x0c:
                return instructionMap.get("fConst1");
            case 0x0d:
                return instructionMap.get("fConst2");
            case 0x0e:
                return instructionMap.get("dConst0");
            case 0x0f:
                return instructionMap.get("dConst1");
            case 0x10:
                return new BIPush();
            case 0x11:
                return new SIPush();
//            case 0x12:
//                return new LDC();
//            case 0x13:
//                return new LDCW();
//            case 0x14:
//                return new LDC2W();
            case 0x15:
                return new ILoad();
            case 0x16:
                return new LLoad();
            case 0x17:
                return new FLoad();
            case 0x18:
                return new DLoad();
            case 0x19:
                return new ALoad();
            case 0x1a:
                return instructionMap.get("iLoad0");
            case 0x1b:
                return instructionMap.get("iLoad1");
            case 0x1c:
                return instructionMap.get("iLoad2");
            case 0x1d:
                return instructionMap.get("iLoad3");
            case 0x1e:
                return instructionMap.get("lLoad0");
            case 0x1f:
                return instructionMap.get("lLoad1");
            case 0x20:
                return instructionMap.get("lLoad2");
            case 0x21:
                return instructionMap.get("lLoad3");
            case 0x22:
                return instructionMap.get("fLoad0");
            case 0x23:
                return instructionMap.get("fLoad1");
            case 0x24:
                return instructionMap.get("fLoad2");
            case 0x25:
                return instructionMap.get("fLoad3");
            case 0x26:
                return instructionMap.get("dLoad0");
            case 0x27:
                return instructionMap.get("dLoad1");
            case 0x28:
                return instructionMap.get("dLoad2");
            case 0x29:
                return instructionMap.get("dLoad3");
            case 0x2a:
                return instructionMap.get("aLoad0");
            case 0x2b:
                return instructionMap.get("aLoad1");
            case 0x2c:
                return instructionMap.get("aLoad2");
            case 0x2d:
                return instructionMap.get("aLoad3");
            // case 0x2e:
            // 	return iaload
            // case 0x2f:
            // 	return laload
            // case 0x30:
            // 	return faload
            // case 0x31:
            // 	return daload
            // case 0x32:
            // 	return aaload
            // case 0x33:
            // 	return baload
            // case 0x34:
            // 	return caload
            // case 0x35:
            // 	return saload
            case 0x36:
                return new IStore();
            case 0x37:
                return new LStore();
            case 0x38:
                return new FStore();
            case 0x39:
                return new DStore();
            case 0x3a:
                return new AStore();
            case 0x3b:
                return instructionMap.get("iStore0");
            case 0x3c:
                return instructionMap.get("iStore1");
            case 0x3d:
                return instructionMap.get("iStore2");
            case 0x3e:
                return instructionMap.get("iStore3");
            case 0x3f:
                return instructionMap.get("lStore0");
            case 0x40:
                return instructionMap.get("lStore1");
            case 0x41:
                return instructionMap.get("lStore2");
            case 0x42:
                return instructionMap.get("lStore3");
            case 0x43:
                return instructionMap.get("fStore0");
            case 0x44:
                return instructionMap.get("fStore1");
            case 0x45:
                return instructionMap.get("fStore2");
            case 0x46:
                return instructionMap.get("fStore3");
            case 0x47:
                return instructionMap.get("dStore0");
            case 0x48:
                return instructionMap.get("dStore1");
            case 0x49:
                return instructionMap.get("dStore2");
            case 0x4a:
                return instructionMap.get("dStore3");
            case 0x4b:
                return instructionMap.get("aStore0");
            case 0x4c:
                return instructionMap.get("aStore1");
            case 0x4d:
                return instructionMap.get("aStore2");
            case 0x4e:
                return instructionMap.get("aStore3");
            // case 0x4f:
            // 	return iastore
            // case 0x50:
            // 	return lastore
            // case 0x51:
            // 	return fastore
            // case 0x52:
            // 	return dastore
            // case 0x53:
            // 	return aastore
            // case 0x54:
            // 	return bastore
            // case 0x55:
            // 	return castore
            // case 0x56:
            // 	return sastore
            default:
                throw new IllegalArgumentException("Unsupported opcode: " + Integer.toHexString(opCode));
        }
    }
}
