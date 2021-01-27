package org.ljf.sjvm.rtda;


/**
 * @author: ljf
 * @date: 2021/1/27 11:44
 * @description: java 虚拟机栈的栈帧
 * maxLocals: uint
 * maxStack: uint
 * java不支持创建long长度的数组
 * @modified By：
 * @version: $ 1.0
 */
public class Frame {
    Frame lower;
    LocalVariableTable localVariableTable;
    OperandStack operandStack;

    public Frame(int maxLocals, int maxStack) {
        this.localVariableTable = new LocalVariableTable(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public LocalVariableTable getLocalVariableTable() {
        return localVariableTable;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }
}
