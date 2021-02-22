package org.ljf.sjvm.instructions;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.SObjectArray;

/**
 * @author: ljf
 * @date: 2021/2/22 9:46
 * @description: <t>aload <t>astore指令的父类
 * @modified By：
 * @version: $ 1.0
 */
public abstract class XInst extends NoOperandInstruction {
    protected SObjectArray beforeExecute(OperandStack operandStack) {
        SObjectArray arrRef = (SObjectArray) operandStack.popRef();
        checkNotNull(arrRef);
        return arrRef;
    }

    protected void checkNotNull(SObjectArray arrRef) {
        if (arrRef == null) {
            throw new NullPointerException();
        }
    }

    protected void checkIndex(int arrLen, int index) {
        if (index < 0 || index >= arrLen) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
