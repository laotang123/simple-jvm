package org.ljf.sjvm.rtda;


import org.ljf.sjvm.rtda.heap.Method;
import org.ljf.sjvm.rtda.heap.Object;

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
    Thread thread;
    Method method;
    int nextPc;//指向下一条指令

    public Thread getThread() {
        return thread;
    }

    public int getNextPc() {
        return nextPc;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }

    @Deprecated
    public Frame(Thread thread, int maxLocals, int maxStack) {
        this.localVariableTable = new LocalVariableTable(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.thread = thread;
    }

    public Frame(Thread thread, Method method) {
        this.localVariableTable = new LocalVariableTable(method.getMaxLocals());
        this.operandStack = new OperandStack(method.getMaxStack());
        this.method = method;
        this.thread = thread;
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

    public Object getRefFromTop(int n){
        return this.operandStack.getRefFromTop(n);
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

    public Slot popSLot() {
        return this.operandStack.popSlot();
    }

    public void pushSlot(Slot slot) {
        this.operandStack.pushSlot(slot);
    }

    public int getInt(int index) {
        return this.localVariableTable.getInt(index);
    }

    public long getLong(int index) {
        return this.localVariableTable.getLong(index);
    }


    public float getFloat(int index) {
        return this.localVariableTable.getLong(index);
    }

    public double getDouble(int index) {
        return this.localVariableTable.getDouble(index);
    }


    public Method getMethod() {
        return method;
    }

    public void setSlot(int index, Slot slot) {
        this.localVariableTable.setSlot(index, slot);
    }
}
