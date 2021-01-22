package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/22 11:20
 * @description: 常量池元素，抽象类
 * @modified By：
 * @version: $ 1.0
 */
public abstract class ConstantInfo {
    //constant pool tags; u1
    private static final short CONSTANT_Class = 7;
    private static final short CONSTANT_Fieldref = 9;
    private static final short CONSTANT_Methodref = 10;
    private static final short CONSTANT_InterfaceMethodref = 11;
    private static final short CONSTANT_String = 8;
    private static final short CONSTANT_Integer = 3;
    private static final short CONSTANT_Float = 4;
    private static final short CONSTANT_Long = 5;
    private static final short CONSTANT_Double = 6;
    private static final short CONSTANT_NameAndType = 12;
    private static final short CONSTANT_Utf8 = 1;
    private static final short CONSTANT_MethodHandle = 15;
    private static final short CONSTANT_MethodType = 16;
    private static final short CONSTANT_InvokeDynamic = 18;


    protected short tag;

    public static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool) {
        short tag = reader.readUint8();
        ConstantInfo constantInfo;

        switch (tag) {
            case CONSTANT_Class:
                constantInfo = createConstantClass(reader, constantPool);
                break;
            case CONSTANT_Fieldref:
                constantInfo = createConstantFieldRef(reader, constantPool);
            case CONSTANT_Methodref:
                constantInfo = createConstantMethodref(reader, constantPool);
                break;
            case CONSTANT_InterfaceMethodref:
                constantInfo = createConstantInterfaceMethodRef(reader, constantPool);
            case CONSTANT_String:
                constantInfo = createConstantString(reader, constantPool);
                break;
            case CONSTANT_Integer:
                constantInfo = createConstantInteger(reader);
            case CONSTANT_Float:
                constantInfo = createConstantFloat(reader);
                break;
            case CONSTANT_Long:
                constantInfo = createConstantLong(reader);
            case CONSTANT_Double:
                constantInfo = createConstantDouble(reader);
                break;
            case CONSTANT_NameAndType:
                constantInfo = createConstantNameAndType(reader);
            case CONSTANT_Utf8:
                constantInfo = createConstantUtf8(reader);
                break;
            case CONSTANT_MethodHandle:
                constantInfo = createConstantMethodHandle(reader);
            case CONSTANT_MethodType:
                constantInfo = createConstantMethodType(reader);
                break;
            case CONSTANT_InvokeDynamic:
                constantInfo = createConstantInvokeDynamic(reader);
                break;
            default:
                throw new IllegalArgumentException("java.lang.ClassFormatError: constant pool tag");

        }

        return constantInfo;
    }

    //TODO: 拿到reader后，按照不同的属性项读取value
    private static ConstantInfo createConstantInvokeDynamic(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantMethodType(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantMethodHandle(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantUtf8(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantNameAndType(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantDouble(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantString(ClassReader reader, ConstantPool constantPoo) {
        return null;
    }

    private static ConstantInfo createConstantMethodref(ClassReader reader, ConstantPool constantPoo) {
        return null;
    }

    private static ConstantInfo createConstantLong(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantFloat(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantInteger(ClassReader reader) {
        return null;
    }

    private static ConstantInfo createConstantInterfaceMethodRef(ClassReader reader, ConstantPool constantPoo) {
        return null;
    }

    private static ConstantInfo createConstantFieldRef(ClassReader reader, ConstantPool constantPoo) {
        return null;
    }

    private static ConstantInfo createConstantClass(ClassReader reader, ConstantPool constantPoo) {
        return null;
    }

}
