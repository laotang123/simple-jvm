package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.rtda.Slot;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/2/1 7:29
 * @description: class的槽位
 * @modified By:
 * @version: $ 1.0
 */
public class Slots implements Cloneable {
    private final Slot[] slots;

    public Slots(int slotCount) {
        if (slotCount < 0) {
            throw new IllegalStateException("slotCount must be non-negative，but get: " + slotCount);
        }
        slots = new Slot[slotCount];
        for (int i = 0; i < slotCount; i++) {
            slots[i] = new Slot();
        }
    }

    public void setInt(int index, int value) {
        slots[index].num = value;
    }

    public int getInt(int index) {
        return slots[index].num;
    }

    public void setFloat(int index, float value) {
        slots[index].num = Float.floatToIntBits(value);
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(slots[index].num);
    }

    //long 占两个槽位
    public void setLong(int index, long value) {
        slots[index].num = (int) value;//低32位
        slots[index + 1].num = (int) (value >> 32);//高32位
    }

    public long getLong(int index) {
        long low = Integer.toUnsignedLong(slots[index].num);
        long high = Integer.toUnsignedLong(slots[index + 1].num);

        return high << 32 | low;
    }

    public void setDouble(int index, double value) {
        long bits = Double.doubleToLongBits(value);
        this.setLong(index, bits);
    }

    public double getDouble(int index) {
        return Double.longBitsToDouble(getLong(index));
    }

    public void setRef(int index, Object ref) {
        slots[index].ref = ref;
    }

    public Object getRef(int index) {
        return slots[index].ref;
    }

    @Override
    public String toString() {
        return "Slots{" +
                "slots=" + Arrays.toString(slots) +
                '}';
    }
}
