package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;

/**
 * @author: ljf
 * @date: 2021/2/21 13:52
 * @description: 创建引用类型的数组类
 * @modified By：
 * @version: $ 1.0
 */
public class ANewArray extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        Class componentClass = classRef.resolvedClass();

        OperandStack operandStack = frame.getOperandStack();
        int count = operandStack.popInt();

        if (count < 0) {
            throw new NegativeArraySizeException();
        }

        ClassArray arrClass = (ClassArray) componentClass.arrayClass();
        SObject arrObject = arrClass.newArray(count);
        operandStack.pushRef(arrObject);
    }
}
