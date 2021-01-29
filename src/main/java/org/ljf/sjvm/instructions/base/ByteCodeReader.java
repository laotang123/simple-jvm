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

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }



    public short readUint8() {
        byte b = this.code[pc];
        this.pc++;
        return (short) (((short) b) & 0xff);
    }

    public short readInt8() {
        byte b = this.code[pc];
        this.pc++;
        return b;
    }

    //待实现 TODO
    public int readUint16() {
        short byte1 = readUint8();
        short byte2 = readUint8();
        return (byte1 << 8) | byte2;
    }

//    public int readInt16(){
//        short byte1 = readUint8();
//        short byte2 = readUint8();
//        return (short) ((byte1 << 8) | byte2);
//    }

    public short readInt16(){
        short byte1 = readUint8();
        short byte2 = readUint8();
        return (short) ((byte1 << 8) | byte2);
    }
}
