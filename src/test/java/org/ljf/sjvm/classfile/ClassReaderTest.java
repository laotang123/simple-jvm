package org.ljf.sjvm.classfile;

import org.junit.Before;
import org.junit.Test;
import org.ljf.sjvm.classpath.Entry;
import org.ljf.sjvm.classpath.EntryFactory;
import org.ljf.sjvm.util.IOUtil;

/**
 * @author: ljf
 * @date: 2021/1/22 10:18
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class ClassReaderTest {
    private static byte[] classData;
    private static final int JDK8_VERSION = 52;

    @Before
    public void loadClass() {
        Entry entry = EntryFactory.newEntry("target/classes");
        String className = "org.ljf.sjvm.book.ch01.Cmd";
        String classPath = IOUtil.getClassPath(className);
        classData = entry.readClass(classPath);
    }

    @Test
    public void readUint32Test() {
        if (classData != null) {
            ClassReader reader = new ClassReader(classData);
            long magic = reader.readUint32();
            System.out.println(magic == 0xCAFEBABE);
        }
    }

    @Test
    public void readUint16Test() {
        if (classData != null) {
            ClassReader reader = new ClassReader(classData, 4);
            int minorVersion = reader.readUint16();
            int majorVersion = reader.readUint16();
            System.out.println(minorVersion == 0);
            System.out.println(majorVersion == JDK8_VERSION);
        }
    }
}
