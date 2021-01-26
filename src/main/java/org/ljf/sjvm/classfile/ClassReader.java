package org.ljf.sjvm.classfile;

import org.ljf.sjvm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ClassReader.class);
    private final byte[] data;
    private int offset;

    public int getOffset() {
        return offset;
    }

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

    //FIXME: 这里超出Int.MaxValue会出现问题，class文件中为uint32
    public byte[] readBytes(long num) {
        int intNum = (int) num;
        int diff = (int) (num - intNum);//uit32-int 差值不会大于int.MaxValue
        if (diff > 0) {
            offset += diff;//多余数据跳过
            logger.warn("cast uint32 to int has error, diff: " + diff);
        }

        return readBytes((int) num);
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

    //类型强转可能会丢失数据，Integer.maxValue到uint32.maxValue之间的数据
    @Deprecated
    public int readUint32ToInt() {
        return (int) readUint32();
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
