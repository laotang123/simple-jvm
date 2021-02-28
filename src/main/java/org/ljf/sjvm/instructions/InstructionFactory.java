package org.ljf.sjvm.instructions;

import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import org.ljf.sjvm.exceptions.UnsupportedException;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.instructions.comparisons.*;
import org.ljf.sjvm.instructions.constants.*;
import org.ljf.sjvm.instructions.control.*;
import org.ljf.sjvm.instructions.conversions.*;
import org.ljf.sjvm.instructions.extended.GotoW;
import org.ljf.sjvm.instructions.extended.IfNonNull;
import org.ljf.sjvm.instructions.extended.IfNull;
import org.ljf.sjvm.instructions.extended.Wide;
import org.ljf.sjvm.instructions.loads.*;
import org.ljf.sjvm.instructions.math.*;
import org.ljf.sjvm.instructions.references.*;
import org.ljf.sjvm.instructions.reserved.InvokeNative;
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

    static {//TODO: 改为final属性
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
        instructionMap.put("iALoad", new XALoad.IALoad());
        instructionMap.put("lALoad", new XALoad.LALoad());
        instructionMap.put("fALoad", new XALoad.FALoad());
        instructionMap.put("dALoad", new XALoad.DALoad());
        instructionMap.put("aALoad", new XALoad.AALoad());
        instructionMap.put("bALoad", new XALoad.BALoad());
        instructionMap.put("cALoad", new XALoad.CALoad());
        instructionMap.put("sALoad", new XALoad.SALoad());
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
        instructionMap.put("iAStore", new XAStore.IAStore());
        instructionMap.put("lAStore", new XAStore.LAStore());
        instructionMap.put("fAStore", new XAStore.FAStore());
        instructionMap.put("dAStore", new XAStore.DAStore());
        instructionMap.put("aAStore", new XAStore.AAStore());
        instructionMap.put("bAStore", new XAStore.BAStore());
        instructionMap.put("cAStore", new XAStore.CAStore());
        instructionMap.put("sAStore", new XAStore.SAStore());
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
        instructionMap.put("_return", new Return());
        instructionMap.put("iReturn", new IReturn());
        instructionMap.put("lReturn", new LReturn());
        instructionMap.put("fReturn", new FReturn());
        instructionMap.put("dReturn", new DReturn());
        instructionMap.put("aReturn", new AReturn());

//        ineg = &INEG { }
//        lneg = &LNEG { }
//        fneg = &FNEG { }
//        dneg = &DNEG { }

        instructionMap.put("arrayLength", new ArrayLength());
//        instructionMap.put("aThrow", new Athrow());
        // athrow        = &ATHROW{}
        // monitorenter  = &MONITOR_ENTER{}
        // monitorexit   = &MONITOR_EXIT{}
        // invoke_native = &INVOKE_NATIVE{}

    }

    //map结构，单例
    //TODO: 根据opCode的最终使用，考虑改为byte类型
    public static Instruction newInstruction(short opCode) throws UnsupportedException {
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
            case 0x12:
                return new Ldc();
            case 0x13:
                return new LdcW();
            case 0x14:
                return new Ldc2W();
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
            case 0x2e:
                return instructionMap.get("iALoad");
            case 0x2f:
                return instructionMap.get("lALoad");
            case 0x30:
                return instructionMap.get("fALoad");
            case 0x31:
                return instructionMap.get("dALoad");
            case 0x32:
                return instructionMap.get("aALoad");
            case 0x33:
                return instructionMap.get("bALoad");
            case 0x34:
                return instructionMap.get("cALoad");
            case 0x35:
                return instructionMap.get("sALoad");
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
            case 0x4f:
                return instructionMap.get("iAStore");
            case 0x50:
                return instructionMap.get("lAStore");
            case 0x51:
                return instructionMap.get("fAStore");
            case 0x52:
                return instructionMap.get("dAStore");
            case 0x53:
                return instructionMap.get("aAStore");
            case 0x54:
                return instructionMap.get("bAStore");
            case 0x55:
                return instructionMap.get("cAStore");
            case 0x56:
                return instructionMap.get("sAStore");
            case 0x57:
                return instructionMap.get("pop");
            case 0x58:
                return instructionMap.get("pop2");
            case 0x59:
                return instructionMap.get("dup");
            case 0x5a:
                return instructionMap.get("dupX1");
            case 0x5b:
                return instructionMap.get("dupX2");
            case 0x5c:
                return instructionMap.get("dup2");
            case 0x5d:
                return instructionMap.get("dup2X1");
            case 0x5e:
                return instructionMap.get("dup2X2");
            case 0x5f:
                return instructionMap.get("swap");
            case 0x60:
                return instructionMap.get("iAdd");
            case 0x61:
                return instructionMap.get("lAdd");
            case 0x62:
                return instructionMap.get("fAdd");
            case 0x63:
                return instructionMap.get("dAdd");
            case 0x64:
                return instructionMap.get("iSub");
            case 0x65:
                return instructionMap.get("lSub");
            case 0x66:
                return instructionMap.get("fSub");
            case 0x67:
                return instructionMap.get("dSub");
            case 0x68:
                return instructionMap.get("iMul");
            case 0x69:
                return instructionMap.get("lMul");
            case 0x6a:
                return instructionMap.get("fMul");
            case 0x6b:
                return instructionMap.get("dMul");
            case 0x6c:
                return instructionMap.get("iDiv");
            case 0x6d:
                return instructionMap.get("lDiv");
            case 0x6e:
                return instructionMap.get("fDiv");
            case 0x6f:
                return instructionMap.get("dDiv");
            case 0x70:
                return instructionMap.get("iRem");
            case 0x71:
                return instructionMap.get("lRem");
            case 0x72:
                return instructionMap.get("fRem");
            case 0x73:
                return instructionMap.get("dRem");
            case 0x74:
                return instructionMap.get("iNeg");
            case 0x75:
                return instructionMap.get("lNeg");
            case 0x76:
                return instructionMap.get("fNeg");
            case 0x77:
                return instructionMap.get("dNeg");
            case 0x78:
                return instructionMap.get("iShl");
            case 0x79:
                return instructionMap.get("lShl");
            case 0x7a:
                return instructionMap.get("iShr");
            case 0x7b:
                return instructionMap.get("lShr");
            case 0x7c:
                return instructionMap.get("iuShr");
            case 0x7d:
                return instructionMap.get("luShr");
            case 0x7e:
                return instructionMap.get("iAnd");
            case 0x7f:
                return instructionMap.get("lAnd");
            case 0x80:
                return instructionMap.get("iOr");
            case 0x81:
                return instructionMap.get("lOr");
            case 0x82:
                return instructionMap.get("iXor");
            case 0x83:
                return instructionMap.get("lXor");
            case 0x84:
                return new IInc();
            case 0x85:
                return instructionMap.get("i2l");
            case 0x86:
                return instructionMap.get("i2f");
            case 0x87:
                return instructionMap.get("i2d");
            case 0x88:
                return instructionMap.get("l2i");
            case 0x89:
                return instructionMap.get("l2f");
            case 0x8a:
                return instructionMap.get("l2d");
            case 0x8b:
                return instructionMap.get("f2i");
            case 0x8c:
                return instructionMap.get("f2l");
            case 0x8d:
                return instructionMap.get("f2d");
            case 0x8e:
                return instructionMap.get("d2i");
            case 0x8f:
                return instructionMap.get("d2l");
            case 0x90:
                return instructionMap.get("d2f");
            case 0x91:
                return instructionMap.get("i2b");
            case 0x92:
                return instructionMap.get("i2c");
            case 0x93:
                return instructionMap.get("i2s");
            case 0x94:
                return instructionMap.get("lCmp");
            case 0x95:
                return instructionMap.get("fCmpL");
            case 0x96:
                return instructionMap.get("fCmpG");
            case 0x97:
                return instructionMap.get("dCmpL");
            case 0x98:
                return instructionMap.get("dCmpG");
            case 0x99:
                return new IfEq();
            case 0x9a:
                return new IfNe();
            case 0x9c:
                return new IfGe();
            case 0x9d:
                return new IfGt();
            case 0x9e:
                return new IfLe();
            case 0x9f:
                return new IfICmpEq();
            case 0xa0:
                return new IfICmpNe();
            case 0xa1:
                return new IfICmpLt();
            case 0xa2:
                return new IfICmpGe();
            case 0xa3:
                return new IfICmpGt();
            case 0xa4:
                return new IfICmpLe();
            case 0xa5:
                return new IfACmpEq();
            case 0xa6:
                return new IfACmpNe();
            case 0xa7:
                return new Goto();
            // case 0xa8:
            // 	return &JSR{}
            // case 0xa9:
            // 	return &RET{}
            case 0xaa:
                return new TableSwitch();
            case 0xab:
                return new LookupSwitch();
            case 0xac:
                return instructionMap.get("iReturn");
            case 0xad:
                return instructionMap.get("lReturn");
            case 0xae:
                return instructionMap.get("fReturn");
            case 0xaf:
                return instructionMap.get("dReturn");
            case 0xb0:
                return instructionMap.get("aReturn");
            case 0xb1:
                return instructionMap.get("_return");
            case 0xb2:
                return new GetStatic();
            case 0xb3:
                return new PutStatic();
            case 0xb4:
                return new GetField();
            case 0xb5:
                return new PutField();
            case 0xb6:
                return new InvokeVirtual();
            case 0xb7:
                return new InvokeSpecial();
            case 0xb8:
                return new InvokeStatic();
            case 0xb9:
                return new InvokeInterface();
            // case 0xba:
            // 	return &INVOKE_DYNAMIC{}
            case 0xbb:
                return new New();
            case 0xbc:
                return new NewArray();
            case 0xbd:
                return new ANewArray();
            case 0xbe:
                return instructionMap.get("arrayLength");
            // case 0xbf:
            // 	return athrow
            case 0xc0:
                return new CheckCast();
            case 0xc1:
                return new Instanceof();
            // case 0xc2:
            // 	return monitorenter
            // case 0xc3:
            // 	return monitorexit
            case 0xc4:
                return new Wide();
            case 0xc5:
                return new MultiANewArray();
            case 0xc6:
                return new IfNull();
            case 0xc7:
                return new IfNonNull();
            case 0xc8:
                return new GotoW();
            // case 0xc9:
            // 	return &JSR_W{}
            // case 0xca: breakpoint
            case 0xfe:
                return new InvokeNative();
                // case 0xff: impdep2

            default:
                throw new UnsupportedException("Unsupported opcode: 0x" + Integer.toHexString(opCode));
        }
    }
}
