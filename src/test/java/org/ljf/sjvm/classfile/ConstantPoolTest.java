package org.ljf.sjvm.classfile;

import org.junit.Before;
import org.junit.Test;
import org.ljf.sjvm.classfile.constantpool.ConstantMemberRefInfo;
import org.ljf.sjvm.classfile.constantpool.ConstantPool;
import org.ljf.sjvm.classpath.Entry;
import org.ljf.sjvm.classpath.EntryFactory;
import org.ljf.sjvm.util.ByteUtils;
import org.ljf.sjvm.util.IOUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/24 8:36
 * @description: 测试常量池相关方法
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantPoolTest {
    private static byte[] classData;
    private static final int JDK8_VERSION = 52;
    private static ClassReader reader;
    private static ClassFile classFile;

    @Before
    public void loadClass() {
        Entry entry = EntryFactory.newEntry(Paths.get(System.getenv("JAVA_HOME"),
                "jre", "lib", "rt.jar").toString());
        String className = "java.lang.Object";
        String classPath = IOUtil.getClassPath(className);
        classData = entry.readClass(classPath);
        reader = new ClassReader(classData);
        classFile = new ClassFile();

        //reader 读取魔数，以及常量池数量
//        long magic = reader.readUint32();
        byte[] magic = reader.readBytes(4);
        System.out.println("magic: " + ByteUtils.bytes2Hex(magic));

        int minorVersion = reader.readUint16();
        int majorVersion = reader.readUint16();
        System.out.println("minor version: " + minorVersion + " major version: " + majorVersion);

        int cpCount = reader.readUint16();
        System.out.println("constant pool count: " + cpCount);
    }

    @Test
    public void MethodRefTest() {
        System.out.println(Arrays.toString(classData));
        ConstantPool constantPool = new ConstantPool();
        ConstantMemberRefInfo.ConstantMethodRefInfo methodRefInfo = new ConstantMemberRefInfo.ConstantMethodRefInfo(constantPool);
        short tag = reader.readUint8();
        System.out.println("tag: " + tag);
        methodRefInfo.readInfo(reader);
        System.out.println(methodRefInfo);

    }

    /**
     * 测试整个常量池读取
     */
    @Test
    public void MainTest() {
        ClassFile classFile = ClassFile.parse(classData);
//        System.out.println(classFile.constantPool.getConstantPoolCount());
    }

    @Test
    public void decodeMUTF8Test() {
        byte[] bytes = new byte[]{112, 49};
        System.out.println(Arrays.toString(bytes));
        String s = ByteUtils.decodeMUTF8(bytes);
        System.out.println(s);
    }


}
