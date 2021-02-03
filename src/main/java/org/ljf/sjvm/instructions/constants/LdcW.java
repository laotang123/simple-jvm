package org.ljf.sjvm.instructions.constants;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.Constant;
import org.ljf.sjvm.rtda.heap.ConstantPool;
import org.ljf.sjvm.rtda.heap.Literal;

/**
 * @author: ljf
 * @date: 2021/2/4 6:48
 * @description: execute执行流程和Ldc类似，不同点是：它的索引是uint16
 * @modified By:
 * @version: $ 1.0
 */
public class LdcW extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Constant constant = constantPool.getConstant(this.index);

        if (constant instanceof Literal.LongLiteral) {
            Literal.LongLiteral longLiteral = (Literal.LongLiteral) constant;
            stack.pushLong(longLiteral.getValue());
        } else if (constant instanceof Literal.DoubleLiteral) {
            Literal.DoubleLiteral doubleLiteral = (Literal.DoubleLiteral) constant;
            stack.pushDouble(doubleLiteral.getValue());
        } else {
            throw new ClassFormatException();
        }
    }
}
