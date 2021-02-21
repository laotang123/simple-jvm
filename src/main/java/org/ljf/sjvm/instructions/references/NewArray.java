package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassLoader;

/**
 * @author: ljf
 * @date: 2021/2/21 13:32
 * @description: 新建数组对象
 * @modified By：
 * @version: $ 1.0
 */
public class NewArray implements Instruction {
    private int arrayType;
    private static final int AT_BOOLEAN = 4;
    private static final int AT_CHAR = 5;
    private static final int AT_FLOAT = 6;
    private static final int AT_DOUBLE = 7;
    private static final int AT_BYTE = 8;
    private static final int AT_SHORT = 9;
    private static final int AT_INT = 10;
    private static final int AT_LONG = 11;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.arrayType = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int count = operandStack.popInt();

        if (count < 0) {
            throw new NegativeArraySizeException();
        }

        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        Class arrClass = getPrimitiveArrayClass(loader);
    }

    private Class getPrimitiveArrayClass(ClassLoader loader) {
        switch (this.arrayType) {
            case AT_BOOLEAN:
                return loader.loadClass("[Z");
            case AT_BYTE:
                return loader.loadClass("[B");
            case AT_CHAR:
                return loader.loadClass("[C");
            case AT_SHORT:
                return loader.loadClass("[S");
            case AT_INT:
                return loader.loadClass("[I");
            case AT_LONG:
                return loader.loadClass("[J");
            case AT_FLOAT:
                return loader.loadClass("[F");
            case AT_DOUBLE:
                return loader.loadClass("[D");
            default:
                throw new IllegalArgumentException("invalid arrayType: " + arrayType);
        }
    }
}
