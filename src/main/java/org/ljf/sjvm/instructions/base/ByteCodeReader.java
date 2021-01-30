package org.ljf.sjvm.instructions.base;

/**
 * @author: ljf
 * @date: 2021/1/28 13:28
 * @description: 字节码读取器
 * @modified By：
 * @version: $ 1.0
 */
public class ByteCodeReader {
    private byte[] code;//存储字节码
    private int pc;//记录读取到了那个字节

    public ByteCodeReader(byte[] code) {
        this.code = code;
        this.pc = 0;
    }

    public ByteCodeReader() {
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public void reset(byte[] code) {
        reset(code, 0);
    }

    public byte readByte() {
        byte b = this.code[pc];
        this.pc++;
        return b;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public short readUint8() {
        return (short) (readByte() & 0xff);
    }

    public short readInt8() {
        return readByte();
    }

    public int readUint16() {
        //&0xff消除负号
        return ((readByte() & 0xff) << 8) |
                ((readByte() & 0xff));
    }


    public short readInt16() {
        short byte1 = readUint8();
        short byte2 = readUint8();
        return (short) ((byte1 << 8) | byte2);
    }

    //大端排序
    public int readInt32() {
        return (((readByte()) << 24) |
                ((readByte() & 0xff) << 16) |
                ((readByte() & 0xff) << 8) |
                ((readByte() & 0xff)));
    }

    public void skipPadding() {
        while (this.pc % 4 != 0) {
            this.readByte();
        }
    }

    public int[] readInt32s(int jumpOffsetsCount) {
        int[] values = new int[jumpOffsetsCount];
        for (int i = 0; i < values.length; i++) {
            values[i] = readInt32();
        }
        return values;
    }

    public boolean isEmpty() {
        return pc == code.length;
    }
}
