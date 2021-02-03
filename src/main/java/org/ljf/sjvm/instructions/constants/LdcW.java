package org.ljf.sjvm.instructions.constants;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.instructions.base.Index8Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.Constant;
import org.ljf.sjvm.rtda.heap.ConstantPool;
import org.ljf.sjvm.rtda.heap.Literal;

/**
 * @author: ljf
 * @date: 2021/2/4 6:40
 * @description: 将运行时常量池中的常量推到操作数栈顶，操作索引编程16位
 * @modified By:
 * @version: $ 1.0
 */
public class LdcW extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        _ldc(frame, this.index);
    }

    static void _ldc(Frame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Constant constant = constantPool.getConstant(index);

        if (constant instanceof Literal.IntegerLiteral) {
            Literal.IntegerLiteral intLiteral = (Literal.IntegerLiteral) constant;
            stack.pushInt(intLiteral.getValue());
        } else if (constant instanceof Literal.FloatLiteral) {
            Literal.FloatLiteral floatLiteral = (Literal.FloatLiteral) constant;
            stack.pushFloat(floatLiteral.getValue());
        }
//        else if(Literal.StringLiteral)
//        else if(ClassRef)
//        else if(MethodType,MethodHandle)
        else {
            throw new IllegalArgumentException("todo: ldc!");
        }
    }
}
