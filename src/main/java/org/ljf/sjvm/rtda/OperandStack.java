package org.ljf.sjvm.rtda;

import org.ljf.sjvm.rtda.heap.SObject;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/27 13:05
 * @description: 栈帧中的操作数栈
 * @modified By：
 * @version: $ 1.0
 */
public class OperandStack {
    private int size;
    private final Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack < 0) {
            throw new IllegalArgumentException("maxStack must be non-negative, but get: " + maxStack);
        }
        slots = new Slot[maxStack];
        for (int i = 0; i < maxStack; i++) {
            slots[i] = new Slot();
        }
    }

    public void pushSlot(Slot slot) {
        this.slots[this.size] = slot;
        this.size++;
    }

    public Slot popSlot() {
        this.size--;
        return this.slots[size];
    }

    public Slot peek() {
        if (size > 0) {
            return this.slots[size - 1];
        }
        return null;
    }

    //FIXME 这里栈顶的对象，都是重用状态
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

    public void pushRef(SObject ref) {
        this.slots[size].ref = ref;
        this.size++;
    }

    public SObject popRef() {
        this.size--;
        SObject ref = this.slots[size].ref;
        this.slots[size].ref = null;//帮助垃圾回收器回收，立刻重用？
        return ref;
    }


    public int getSize() {
        return size;
    }

    public Slot[] getSlots() {
        return slots;
    }


    @Override
    public String toString() {
        return "OperandStack{" +
                "size=" + size +
                ", slots=" + Arrays.toString(slots) +
                '}';
    }

    public SObject getRefFromTop(int n) {
        return this.slots[this.size - 1 - n].ref;
    }

    public void pushBoolean(boolean val) {
        if (val) {
            this.pushInt(1);
        } else {
            this.pushInt(0);
        }
    }

    public void clear() {
        this.size = 0;
        for (Slot slot : this.slots) {
            slot.ref = null;
        }
    }
}
