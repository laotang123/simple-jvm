package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.rtda.heap.SObjectArray;

/**
 * @author: ljf
 * @date: 2021/2/22 7:15
 * @description: 获取数组对象的长度
 * @modified By:
 * @version: $ 1.0
 */
public class ArrayLength extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        SObjectArray arrRef = (SObjectArray)operandStack.popRef();
        if (arrRef == null){
            throw new NullPointerException();
        }

        operandStack.pushInt(arrRef.arrayLength());
    }
}
