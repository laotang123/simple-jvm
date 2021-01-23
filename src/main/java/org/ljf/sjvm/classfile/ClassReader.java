package org.ljf.sjvm.classfile;

import org.ljf.sjvm.classfile.constantpool.ConstantPool;
import org.ljf.sjvm.util.ByteUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigInteger;

/**
 * @author: ljf
 * @date: 2021/1/22 6:47
 * @description: 读取字节流的不同单位大小
 * @modified By:
 * @version: $ 1.0
 */
public class ClassReader {
    private final byte[] data;
    private int offset;

    public ClassReader(byte[] data) {
        this.data = data;
        this.offset = 0;
    }

    public ClassReader(byte[] data, int offset) {
        this.data = data;
        this.offset = offset;
    }

    public byte[] readBytes(int num) {
        byte[] values = new byte[num];
        System.arraycopy(this.data, offset, values, 0, num);
        offset += num;
        return values;
    }

    //u1
    public short readUint8() {
        return ByteUtils.byte2Uint8(this.data[offset++]);
    }

    //u2
    public int readUint16() {
        int value = ByteUtils.byte2Uint16(this.data, offset);
        offset += 2;
        return value;
    }

    public int[] readUint16s() {
        int len = ByteUtils.byte2Uint16(this.data, offset);
        offset += 2;
        int[] values = new int[len];
        for (int i = 0; i < len; i++) {
            values[i] = readUint16();
        }
        return values;
    }

    public long readUint32() {
        long value = ByteUtils.byte2Uint32(this.data, offset);
        offset += 4;
        return value;
    }

    public int readInt32() {
        int value = ByteUtils.byte2int32(this.data, offset);
        offset += 4;
        return value;
    }

    public long readInt64() {
        long value = ByteUtils.byte2int64(this.data, offset);
        offset += 8;
        return value;
    }

    public float readFloat() {
        int i = readInt32();
        return Float.intBitsToFloat(i);
    }

    public double readDouble() {
        long l = readInt64();
        return Double.longBitsToDouble(l);
    }

    public BigInteger readUint64() {
        throw new NotImplementedException();
    }

}
