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

    public void pushRef(Object ref) {
        this.operandStack.pushRef(ref);
    }

    public Object popRef() {
        return operandStack.popRef();
    }

    public void pushDouble(double value) {
        this.operandStack.pushDouble(value);
    }

    public double popDouble() {
        return operandStack.popDouble();
    }

    public void pushInt(int value) {
        this.operandStack.pushInt(value);
    }

    public int popInt() {
        return operandStack.popInt();
    }

    public void pushFloat(float value) {
        this.operandStack.pushFloat(value);
    }

    public float popFloat() {
        return operandStack.popFloat();
    }

    public void pushLong(long value) {
        this.operandStack.pushLong(value);
    }

    public long popLong() {
        return operandStack.popLong();
    }

    public void setRef(int index, Object ref) {
        this.localVariableTable.setRef(index, ref);
    }

    public void setDouble(int index, double value) {
        this.localVariableTable.setDouble(index, value);
    }

    public void setFloat(int index, float value) {
        this.localVariableTable.setFloat(index, value);
    }

    public void setLong(int index, long value) {
        this.localVariableTable.setLong(index, value);
    }

    public void setInt(int index, int value) {
        this.localVariableTable.setInt(index, value);
    }
}
