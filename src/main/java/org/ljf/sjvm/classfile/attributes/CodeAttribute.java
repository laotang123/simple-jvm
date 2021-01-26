package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;
import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classfile.constantpool.ConstantPool;

/**
 * @author: ljf
 * @date: 2021/1/25 15:58
 * @description: Code_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 max_stack;
 * u2 max_locals;
 * u4 code_length;
 * u1 code[code_length];
 * u2 exception_table_length;
 * { u2 start_pc;
 * u2 end_pc;
 * u2 handler_pc;
 * u2 catch_type;
 * } exception_table[exception_table_length];
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class CodeAttribute implements AttributeInfo {
    private final ConstantPool constantPool;
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private ExceptionTableEntry[] exceptionTable;
    private AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public ExceptionTableEntry[] getExceptionTable() {
        return exceptionTable;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        long codeLength = reader.readUint32();
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = readExceptionTable(reader);
        this.attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    private static ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        int len = reader.readUint16();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[len];
        for (int i = 0; i < len; i++) {
            ExceptionTableEntry entry = new ExceptionTableEntry();
            entry.readInfo(reader);
            exceptionTable[i] = entry;
        }

        return exceptionTable;
    }

    private static class ExceptionTableEntry implements AttributeInfo {
        private int startPc;
        private int endPc;
        private int handlerPc;
        private int catchType;

        public int getStartPc() {
            return startPc;
        }


        public int getEndPc() {
            return endPc;
        }


        public int getHandlerPc() {
            return handlerPc;
        }


        public int getCatchType() {
            return catchType;
        }


        @Override
        public void readInfo(ClassReader reader) {
            this.startPc = reader.readUint16();
            this.endPc = reader.readUint16();
            this.handlerPc = reader.readUint16();
            this.catchType = reader.readUint16();
        }
    }
}
