package org.ljf.sjvm.rtda.heap;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/2/21 11:18
 * @description: 对象数组
 * @modified By：
 * @version: $ 1.0
 */
public class SObjectArray extends ArraySObject {
    private final Object[] items;

    public SObjectArray(Class clazz, Object[] items) {
        super(clazz, items.length);
        this.items = items;
    }

    public static void arrayCopy(SObject src, SObject dest, int srcPos, int destPos, int length) {
        SObjectArray srcArray = (SObjectArray) src;
        SObjectArray destArray = (SObjectArray) dest;

        System.arraycopy(srcArray.items, srcPos, destArray.items, destPos, length);
    }

    //将items转为指定类型的数据
    public byte[] bytes() {
        byte[] res = new byte[this.len];
        for (int i = 0; i < this.len; i++) {
            res[i] = (byte) items[i];
        }
        return res;
    }

    public short[] shorts() {
        short[] res = new short[this.len];
        for (int i = 0; i < this.len; i++) {
            res[i] = (short) items[i];
        }
        return res;
    }

    public int[] ints() {
        int[] res = new int[this.len];
        for (int i = 0; i < this.len; i++) {
            if (items[i] == null) {
                break;
            }
            res[i] = (int) items[i];
        }
        return res;
    }

    public long[] longs() {
        long[] res = new long[this.len];
        for (int i = 0; i < this.len; i++) {
            res[i] = (long) items[i];
        }
        return res;
    }

    public float[] floats() {
        float[] res = new float[this.len];
        for (int i = 0; i < this.len; i++) {
            res[i] = (float) items[i];
        }
        return res;
    }

    public double[] doubles() {
        double[] res = new double[this.len];
        for (int i = 0; i < this.len; i++) {
            res[i] = (double) items[i];
        }
        return res;
    }


    public char[] chars() {
        char[] res = new char[this.len];
        for (int i = 0; i < this.len; i++) {
            res[i] = (char) items[i];
        }
        return res;
    }


    public Object[] refs() {
        return items;
    }


    public int arrayLength() {
        return this.len;
    }

    public void setItem(int index, Object item) {
        this.items[index] = item;
    }

    @Override
    public String toString() {
        return "SObjectArray{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
