package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.*;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/2/21 13:52
 * @description: 创建引用类型的多维数组类
 * @modified By：
 * @version: $ 1.0
 */
public class MultiANewArray implements Instruction {
    private int index;
    private int dimensions;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint16();
        this.dimensions = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        Class arrClass = classRef.resolvedClass();

        OperandStack stack = frame.getOperandStack();
        int[] counts = popAndCheckCounts(stack);
        SObject arrSObject = newMultiDimensionalArray(counts, arrClass);
        stack.pushRef(arrSObject);
    }

    private SObject newMultiDimensionalArray(int[] counts, Class arrClass) {
        int count = counts[0];
        SObject array = ((ClassArray) arrClass).newArray(count);

        if (counts.length > 1) {
            Object[] refs = ((SObjectArray) array).refs();
            int[] subCounts = new int[counts.length - 1];
            System.arraycopy(counts, 1, subCounts, 0, counts.length - 1);

            for (int i = 0; i < refs.length; i++) {
                refs[i] = newMultiDimensionalArray(subCounts, ((ClassArray) arrClass).componentClass());
            }

        }

        return array;
    }


    private int[] popAndCheckCounts(OperandStack stack) {
        //栈弹出顺序 从数组外维度向内填充
        int[] counts = new int[this.dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            counts[i] = stack.popInt();
            if (counts[i] < 0) {
                throw new NegativeArraySizeException();
            }
        }

        return counts;
    }
}
