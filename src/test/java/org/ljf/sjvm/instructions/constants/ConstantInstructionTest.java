package org.ljf.sjvm.instructions.constants;

import org.junit.Before;
import org.junit.Test;
import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Thread;

import static org.junit.Assert.*;

/**
 * @author: ljf
 * @date: 2021/1/29 10:50
 * @description: 常量指令测试
 * @modified By：
 * @version: $ 1.0
 */
public class ConstantInstructionTest {
    private static final ByteCodeReader reader = new ByteCodeReader();
    private static final Thread thread = new Thread();
    private static final Frame frame = thread.newFrame(1024, 1024);

    @Before
    public void before() {
        byte[] code = {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
        reader.reset(code);
    }

    @Test
    public void testBIPush() {
        BIPush biPush = new BIPush();
        biPush.fetchOperands(reader);
        biPush.execute(frame);

        int expected = frame.popInt();
        System.out.println(expected);
        assertEquals(expected, -1);
    }

    @Test
    public void testSIPush() {
        SIPush siPush = new SIPush();
        siPush.fetchOperands(reader);
        siPush.execute(frame);

        int expected = frame.popInt();
        System.out.println(expected);
        assertEquals(expected, -1);
    }
}
