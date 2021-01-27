package org.ljf.sjvm.rtda;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author: ljf
 * @date: 2021/1/27 13:05
 * @description: 栈帧中的操作数栈
 * @modified By：
 * @version: $ 1.0
 */
public class OperandStack {
    private int size;
    private Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack < 0) {
            throw new IllegalArgumentException("maxStack must be non-negative, but get: " + maxStack);
        }
        slots = new Slot[maxStack];
        for (int i = 0; i < maxStack; i++) {
            slots[i] = new Slot();
        }
    }


    public void pushInt(int value) {
        this.slots[size].num = value;
        this.size++;
    }

    public int popInt() {
        this.size--;
        return this.slots[size].num;
    }

    public void pushFloat(float value) {
        this.slots[size].num = Float.floatToIntBits(value);
        this.size++;
    }

    public float popFloat() {
        this.size--;
        return Float.intBitsToFloat(slots[size].num);
    }

    public void pushLong(long value) {
        slots[size].num = (int) value;//低32位
        slots[size + 1].num = (int) (value >> 32);//高32位
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        long low = Integer.toUnsignedLong(slots[size].num);
        long high = Integer.toUnsignedLong(slots[size + 1].num);

        return high << 32 | low;
    }

    public void pushDouble(double value) {
        long bits = Double.doubleToLongBits(value);
        this.pushLong(bits);
    }

    public double popDouble() {
        long bits = this.popLong();
        return Double.longBitsToDouble(bits);
    }

    public void pushRef(Object ref) {
        this.slots[size].ref = ref;
        this.size++;
    }

    public Object popRef() {
        this.size--;
        Object ref = this.slots[size].ref;
        this.slots[size].ref = null;//帮助垃圾回收器回收，立刻重用？
        return ref;
    }
}