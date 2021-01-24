package org.ljf.sjvm.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;

/**
 * @author: ljf
 * @date: 2021/1/22 7:13
 * @description: 数组处理工具
 * @modified By:
 * @version: $ 1.0
 */
public class ByteUtils {
//    public static BigInteger getUint64() {
//        return new BigInteger(1, new byte[]{0b0, 0b0, 0b0, 0b0, 0b0, 0b0, 0b0, 0b0});
//    }

    //u1
    public static short byte2Uint8(byte b) {
        return (short) (b & 0xff);
    }

    //u2
    public static int byte2Uint16(byte[] bytes, int start) {
        return bytes2Int(bytes, start, 2);
    }

    //u4，TODO：uint32应该用long表示？, magic处理为uint32有问题！。。
    public static long byte2Uint32(byte[] bytes, int start) {
        return bytes2Long(bytes, 0, 4);
    }

    //u8，TODO：uint64需要自定义类？
    public static int byte2Uint64(byte[] bytes, int start) {
        return bytes2Int(bytes, 0, 8);
    }

    static private int makeInt(byte b3, byte b2, byte b1, byte b0) {
        return (((b3) << 24) |
                ((b2 & 0xff) << 16) |
                ((b1 & 0xff) << 8) |
                ((b0 & 0xff)));
    }

    //有符号int32，大端排序
    public static int byte2int32(byte[] bytes, int start) {

        return makeInt(bytes[start], bytes[start + 1], bytes[start + 2], bytes[start + 3]);
    }

    //有符号64位 long
    public static long byte2int64(byte[] bytes, int start) {
        return bytes2Long(bytes, 0, 8);
    }

    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }


    public static long bytes2Long(byte[] b, int start, int len) {
        long sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            short n = (short) (b[i] & 0xff);
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    public static byte[] string2Bytes(String str) {
        return str.getBytes();
    }

    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length, originalBytes.length - offset - len);
        return newBytes;
    }

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 一个字节为8位，4位表示16进制。故一个字节可以表示两个16进制的数
     */
    public static String bytes2Hex(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    /**
     * 字符串在class文件中是MUTF8编码的
     * MUTF-8编码方式和UTF-8大致相同，但并不兼容。 差别有两点：
     * 一是null字符（代码点U+0000）会被编码成2字节： 0xC0、0x80；
     * 二是补充字符（Supplementary Characters，代码点大于 U+FFFF的Unicode字符）是按UTF-16拆分为代理对（Surrogate Pair） 分别编码的。
     * 参考链接：
     * http://stackoverflow.com/questions/15440584/why-does-java-use- modified-utf-8-instead-of-utf-8。
     * http://www.oracle.com/technetwork/articles/javase/supplementary- 142654.html。
     *
     * @param bytes：带编码字节数组
     * @return ：编码后字符串
     */
    public static String decodeMUTF8(byte[] bytes) {
        String result = null;
        try {
            result = decodeMUTF8V2(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String decodeMUTF8V2(byte[] bytes) throws IOException {
        int utflen = bytes.length;
        byte[] bytearr = bytes;
        char[] chararr = new char[utflen];

//        bytearr = new byte[utflen];
//        bytearr = bytes;
//        chararr = new char[utflen];


        int c, char2, char3;
        int count = 0;
        int chararr_count = 0;

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            if (c > 127) break;
            count++;
            chararr[chararr_count++] = (char) c;
        }

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    /* 0xxxxxxx*/
                    count++;
                    chararr[chararr_count++] = (char) c;
                    break;
                case 12:
                case 13:
                    /* 110x xxxx   10xx xxxx*/
                    count += 2;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                                "malformed input: partial character at end");
                    char2 = (int) bytearr[count - 1];
                    if ((char2 & 0xC0) != 0x80)
                        throw new UTFDataFormatException(
                                "malformed input around byte " + count);
                    chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
                            (char2 & 0x3F));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                                "malformed input: partial character at end");
                    char2 = (int) bytearr[count - 2];
                    char3 = (int) bytearr[count - 1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
                        throw new UTFDataFormatException(
                                "malformed input around byte " + (count - 1));
                    chararr[chararr_count++] = (char) (((c & 0x0F) << 12) |
                            ((char2 & 0x3F) << 6) |
                            ((char3 & 0x3F) << 0));
                    break;
                default:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDataFormatException(
                            "malformed input around byte " + count);
            }
        }
        // The number of chars produced may be less than utflen
        return new String(chararr, 0, chararr_count);
    }
}
