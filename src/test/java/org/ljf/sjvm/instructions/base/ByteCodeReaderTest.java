package org.ljf.sjvm.instructions.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: ljf
 * @date: 2021/1/29 9:23
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class ByteCodeReaderTest {
    private static final ByteCodeReader reader = new ByteCodeReader();

    @Before
    public void before() {
        byte[] code = {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
        reader.reset(code);
    }

    @Test
    public void testReadInt8() {
        reader.setPc(0);
        short expected = reader.readInt8();
        System.out.println(expected);
        assertEquals(expected, (short) (-1));
    }

    @Test
    public void testReadUint8() {
        reader.setPc(0);
        short expected = reader.readUint8();
        System.out.println(expected);
        assertEquals(expected, (short) (255));
    }

    @Test
    public void testReadUint16() {
        reader.setPc(0);
        int expected = reader.readUint16();
        System.out.println(expected);
        assertEquals(expected, 65535);
    }

    @Test
    public void testReadInt16() {
        reader.setPc(0);
        int expected = reader.readInt16();
        System.out.println(expected);
        assertEquals(expected, -1);
    }

    @Test
    public void testReadInt32() {
        byte[] code = {(byte) 0x7f, (byte) 0xff, (byte) 0xff, (byte) 0xff};
        reader.reset(code);
        int expected = reader.readInt32();
        System.out.println(expected);
        assertEquals(expected, 2147483647);
        assertEquals(expected, Integer.MAX_VALUE);
    }
}
