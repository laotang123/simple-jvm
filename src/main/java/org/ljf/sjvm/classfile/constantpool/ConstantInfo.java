package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/22 11:20
 * @description: 常量池元素，抽象类
 * @modified By：
 * @version: $ 1.0
 */
public interface ConstantInfo {
    //constant pool tags; u1
    short CONSTANT_Class = 7;
    short CONSTANT_Fieldref = 9;
    short CONSTANT_Methodref = 10;
    short CONSTANT_InterfaceMethodref = 11;
    short CONSTANT_String = 8;
    short CONSTANT_Integer = 3;
    short CONSTANT_Float = 4;
    short CONSTANT_Long = 5;
    short CONSTANT_Double = 6;
    short CONSTANT_NameAndType = 12;
    short CONSTANT_Utf8 = 1;
    short CONSTANT_MethodHandle = 15;
    short CONSTANT_MethodType = 16;
    short CONSTANT_InvokeDynamic = 18;


    //    protected short tag;
    void readInfo(ClassReader reader);

    /**
     * 先读取tag，然后根据根据tag创建不同的ConstantInfo实例
     *
     * @param reader：类读取器
     * @param constantPool：常量池实例
     * @return ：常量池属性表元素
     */
    static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool) {
        short tag = reader.readUint8();
        ConstantInfo constantInfo = newConstantInfo(tag, constantPool);
        constantInfo.readInfo(reader);
        return constantInfo;
    }

    static ConstantInfo newConstantInfo(short tag, ConstantPool constantPool) {
        switch (tag) {
            case CONSTANT_Class:
                return new ConstantClassInfo(constantPool);
            case CONSTANT_Fieldref:
                return new ConstantMemberRefInfo.ConstantFieldRefInfo(constantPool);
            case CONSTANT_Methodref:
                return new ConstantMemberRefInfo.ConstantMethodRefInfo(constantPool);
            case CONSTANT_InterfaceMethodref:
                return new ConstantMemberRefInfo.ConstantInterfaceMethodRefInfo(constantPool);
            case CONSTANT_String:
                return new ConstantStringInfo(constantPool);
            case CONSTANT_Integer:
                return new NumericInfo.ConstantIntegerInfo();
            case CONSTANT_Float:
                return new NumericInfo.ConstantFloatInfo();
            case CONSTANT_Long:
                return new NumericInfo.ConstantLongInfo();
            case CONSTANT_Double:
                return new NumericInfo.ConstantDoubleInfo();
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo();
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo();
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo();
            default:
                throw new IllegalArgumentException("java.lang.ClassFormatError: constant pool tag");

        }
    }


}
