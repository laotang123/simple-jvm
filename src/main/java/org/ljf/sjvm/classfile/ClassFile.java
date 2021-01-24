package org.ljf.sjvm.classfile;

import org.ljf.sjvm.classfile.constantpool.ConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/22 7:58
 * @description: class文件结构
 * ClassFile {
 * u4 magic;
 * u2 minor_version;
 * u2 major_version;
 * u2 constant_pool_count;
 * cp_info constant_pool[constant_pool_count-1];
 * u2 access_flags;
 * u2 this_class;
 * u2 super_class;
 * u2 interfaces_count;
 * u2 interfaces[interfaces_count];
 * u2 fields_count;
 * field_info fields[fields_count];
 * u2 methods_count;
 * method_info methods[methods_count];
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ClassFile {
    private static final Logger logger = LoggerFactory.getLogger(ClassFile.class);
    //long magic //uint32 u4
    int minorVersion;//uint16 u2
    int majorVersion; //uint16 u2
    ConstantPool constantPool;
    int accessFlags; //uint16 u2
    int thisClass; //uint16 u2
    int superClass; //uint16 u2
    int[] interfaces; //uint16[] u2
    MemberInfo[] fields;
    MemberInfo[] methods;
    AttributeInfo[] attributes;


    /**
     * 将字节数组解析为ClassFile结构
     *
     * @param classData：class数组
     * @return ：classFile实例
     */
    public static ClassFile parse(byte[] classData) {
        ClassReader classReader = new ClassReader(classData);
        ClassFile classFile = new ClassFile();
        classFile.read(classReader);
        return classFile;
    }

    /**
     * reader 将字节数组转化为classFile中的属性
     */
    private void read(ClassReader reader) {
        this.readAndCheckMagic(reader);
        this.readAndCheckVersion(reader);
        this.constantPool = readConstantPool(reader);
        logger.info("end of constant pool offset: " + reader.getOffset());

    }

    private ConstantPool readConstantPool(ClassReader reader) {
        return new ConstantPool(reader);
    }

    private boolean rightVersion(int majorVersion, int minorVersion) {
        //TODO:魔数修改为有意义常量
        if (majorVersion == 45) {
            return true;
        } else {
            return majorVersion >= 46 && majorVersion <= 52 && minorVersion == 0;
        }
    }

    private void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();

        logger.info("minor version: " + minorVersion + " major version: " + majorVersion);
        if (!rightVersion(majorVersion, minorVersion)) {
            throw new IllegalArgumentException("java.lang.UnsupportedClassVersionError: majorVersion="
                    + majorVersion + "minorVersion=" + minorVersion);
        }

    }

    private void readAndCheckMagic(ClassReader reader) {
        long magic = reader.readInt32();
        if (magic != 0xCAFEBABE) {
            throw new IllegalArgumentException("java.lang.ClassFormatError: magic!");
        }
    }
}
