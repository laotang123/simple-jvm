package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/23 11:05
 * @description: CONSTANT_Fieldref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * CONSTANT_Methodref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * CONSTANT_InterfaceMethodref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * @modified By:
 * @version: $ 1.0
 */
public abstract class MemberRefInfo implements ConstantInfo {
    protected int classIndex;
    protected int nameAndTypeIndex;
    protected ConstantPool constantPool;

    public String className() {
        return this.constantPool.getClassName(classIndex);
    }

    public ConstantNameAndTypeInfo NameAndDescriptor() {
        return this.constantPool.getNameAndType(nameAndTypeIndex);
    }

    static class ConstantMethodRefInfo extends MemberRefInfo {

        public ConstantMethodRefInfo(ConstantPool constantPool) {
            this.constantPool = constantPool;
        }

        @Override
        public void readInfo(ClassReader reader) {
            this.classIndex = reader.readUint16();
            this.nameAndTypeIndex = reader.readUint16();
        }
    }

    static class ConstantFieldRefInfo extends MemberRefInfo {

        public ConstantFieldRefInfo(ConstantPool constantPool) {
            this.constantPool = constantPool;
        }

        @Override
        public void readInfo(ClassReader reader) {
            this.classIndex = reader.readUint16();
            this.nameAndTypeIndex = reader.readUint16();
        }
    }

    static class ConstantInterfaceMethodRefInfo extends MemberRefInfo {

        public ConstantInterfaceMethodRefInfo(ConstantPool constantPool) {
            this.constantPool = constantPool;
        }


        @Override
        public void readInfo(ClassReader reader) {
            this.classIndex = reader.readUint16();
            this.nameAndTypeIndex = reader.readUint16();
        }
    }

}
