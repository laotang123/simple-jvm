package org.ljf.sjvm.instructions.stores;

import org.ljf.sjvm.instructions.XInst;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.rtda.heap.SObjectArray;

/**
 * @author: ljf
 * @date: 2021/2/22 7:22
 * @description: 数组对象store指令
 * @modified By:
 * @version: $ 1.0
 */
public abstract class XAStore extends XInst {
    public static class AAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            SObject ref = operandStack.popRef();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            Object[] refs = arrRef.refs();
            checkIndex(refs.length, index);
            refs[index] = ref;
        }
    }

    public static class BAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int value = operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            byte[] bytes = arrRef.bytes();
            checkIndex(bytes.length, index);
            bytes[index] = (byte) value;
        }
    }

    public static class CAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int value = operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            char[] chars = arrRef.chars();
            checkIndex(chars.length, index);
            chars[index] = (char) value;
        }
    }

    public static class DAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            double value = operandStack.popDouble();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            double[] doubles = arrRef.doubles();
            checkIndex(doubles.length, index);
            doubles[index] = value;
        }
    }

    public static class FAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            float value = operandStack.popFloat();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            float[] floats = arrRef.floats();
            checkIndex(floats.length, index);
            floats[index] = value;
        }
    }

    public static class IAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int value = operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            int[] ints = arrRef.ints();
            checkIndex(ints.length, index);
            ints[index] = value;
        }
    }

    public static class LAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            long value = operandStack.popLong();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            long[] longs = arrRef.longs();
            checkIndex(longs.length, index);
            longs[index] = value;
        }
    }

    public static class SAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int value = operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            short[] shorts = arrRef.shorts();
            checkIndex(shorts.length, index);
            shorts[index] = (short) value;
        }
    }
}
