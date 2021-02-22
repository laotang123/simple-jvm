package org.ljf.sjvm.instructions.loads;

import org.apache.commons.lang3.ObjectUtils;
import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.rtda.heap.SObjectArray;

/**
 * @author: ljf
 * @date: 2021/2/22 7:22
 * @description: 数组对象load指令
 * @modified By:
 * @version: $ 1.0
 */
public abstract class XALoad extends NoOperandInstruction {

    protected SObjectArray beforeExecute(OperandStack operandStack) {
        SObjectArray arrRef = (SObjectArray) operandStack.popRef();
        checkNotNull(arrRef);
        return arrRef;
    }

    private void checkNotNull(SObjectArray arrRef) {
        if (arrRef == null) {
            throw new NullPointerException();
        }
    }

    public static class AALoad extends XALoad {


        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            Object[] refs = arrRef.refs();
            checkIndex(refs.length, index);
            operandStack.pushRef((SObject) refs[index]);
        }


    }


    public static class BALoad extends XALoad {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            byte[] bytes = arrRef.bytes();
            checkIndex(bytes.length, index);
            operandStack.pushInt(bytes[index] & 0xff); //uint8
        }
    }

    public static class CALoad extends XALoad {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            char[] chars = arrRef.chars();
            checkIndex(chars.length, index);
            operandStack.pushInt(chars[index] & 0xffff); //uint16 FIXME: 这样是否正确？
        }
    }

    public static class DALoad extends XALoad {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            double[] doubles = arrRef.doubles();
            checkIndex(doubles.length, index);
            operandStack.pushDouble(doubles[index]);
        }
    }

    public static class FALoad extends XALoad {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            float[] floats = arrRef.floats();
            checkIndex(floats.length, index);
            operandStack.pushFloat(floats[index]);
        }
    }

    public static class IALoad extends XALoad {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            int[] ints = arrRef.ints();
            checkIndex(ints.length, index);
            operandStack.pushInt(ints[index]);
        }
    }

    public static class LALoad extends XALoad {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            long[] longs = arrRef.longs();
            checkIndex(longs.length, index);
            operandStack.pushLong(longs[index]);
        }
    }

    public static class SALoad extends XALoad {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            short[] shorts = arrRef.shorts();
            checkIndex(shorts.length, index);
            operandStack.pushInt(shorts[index]);
        }
    }


    protected void checkIndex(int arrLen, int index) {
        if (index < 0 || index >= arrLen) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
