package org.ljf.sjvm.instructions.stores;

import org.ljf.sjvm.Interpreter;
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
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index, ref);
        }
    }

    public static class BAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            byte value = (byte)operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index, value);
        }
    }

    public static class CAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            char value = (char)operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index, value);
        }
    }

    public static class DAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            double value = operandStack.popDouble();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index, value);
        }
    }

    public static class FAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            float value = operandStack.popFloat();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index, value);
        }
    }

    public static class IAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            int value = operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index, value);
        }
    }

    public static class LAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            long value = operandStack.popLong();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index,value);
        }
    }

    public static class SAStore extends XAStore {
        @Override
        public void execute(Frame frame) {
            OperandStack operandStack = frame.getOperandStack();
            short value = (short)operandStack.popInt();
            int index = operandStack.popInt();

            SObjectArray arrRef = beforeExecute(operandStack);
            checkIndex(arrRef.arrayLength(), index);
            arrRef.setItem(index, value);
        }
    }
}
