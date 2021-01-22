package org.ljf.sjvm.util;

import java.math.BigInteger;

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

    //u4，TODO：uint32应该用long表示？
    public static long byte2Uint32(byte[] bytes, int start) {
        return bytes2Long(bytes, 0, 4);
    }

    //u4，TODO：uint64需要自定义类？
    public static int byte2Uint64(byte[] bytes, int start) {
        return bytes2Int(bytes, 0, 8);
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
}
