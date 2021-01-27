package org.ljf.sjvm.rtda;

/**
 * @author: ljf
 * @date: 2021/1/28 7:30
 * @description: 操作数栈测试
 * @modified By:
 * @version: $ 1.0
 */
public class OperandStackTest {
    public static void main(String[] args) {
        OperandStack operandStack = new OperandStack(10);

        //int
        operandStack.pushInt(Integer.MAX_VALUE);
        System.out.println(operandStack.popInt() == Integer.MAX_VALUE);
        operandStack.pushInt(Integer.MIN_VALUE);
        System.out.println(operandStack.popInt() == Integer.MIN_VALUE);

        //long
        operandStack.pushLong(Long.MAX_VALUE);
        System.out.println(operandStack.popLong() == Long.MAX_VALUE);
        operandStack.pushLong(Long.MIN_VALUE);
        System.out.println(operandStack.popLong() == Long.MIN_VALUE);

        //float
        operandStack.pushFloat(Float.MAX_VALUE);
        System.out.println(operandStack.popFloat() == Float.MAX_VALUE);
        operandStack.pushFloat(Float.MIN_VALUE);
        System.out.println(operandStack.popFloat() == Float.MIN_VALUE);

        //double
        operandStack.pushDouble(Double.MAX_VALUE);
        System.out.println(operandStack.popDouble() == Double.MAX_VALUE);
        operandStack.pushDouble(Double.MIN_VALUE);
        System.out.println(operandStack.popDouble() == Double.MIN_VALUE);

        //ref
        operandStack.pushRef(null);
        System.out.println(operandStack.popRef() == null);
    }
}
