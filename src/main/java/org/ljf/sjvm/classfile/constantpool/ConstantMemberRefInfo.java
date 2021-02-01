package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public abstract class ConstantMemberRefInfo implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(ConstantMemberRefInfo.class);

    protected int classIndex;
    protected int nameAndTypeIndex;
    protected ConstantPool constantPool;

    public String className() {
        return this.constantPool.getClassName(classIndex);
    }

    public String[] NameAndDescriptor() {
        return this.constantPool.getNameAndType(nameAndTypeIndex);
    }

    public static class ConstantMethodRefInfo extends ConstantMemberRefInfo {

        public ConstantMethodRefInfo(ConstantPool constantPool) {
            this.constantPool = constantPool;
        }

        @Override
        public void readInfo(ClassReader reader) {
            this.classIndex = reader.readUint16();
            this.nameAndTypeIndex = reader.readUint16();
            logger.info("end of ConstantMethodRefInfo offset: " + reader.getOffset() + " classIndex: " + classIndex
                    + " nameAndTypeIndex: " + nameAndTypeIndex);
        }

        @Override
        public String toString() {
            return "ConstantMethodRefInfo{" +
                    "classIndex=" + classIndex +
                    ", nameAndTypeIndex=" + nameAndTypeIndex +
                    ", constantPool=" + constantPool +
                    '}';
        }
    }

    public static class ConstantFieldRefInfo extends ConstantMemberRefInfo {

        public ConstantFieldRefInfo(ConstantPool constantPool) {
            this.constantPool = constantPool;
        }

        @Override
        public void readInfo(ClassReader reader) {
            this.classIndex = reader.readUint16();
            this.nameAndTypeIndex = reader.readUint16();
            logger.info("end of ConstantFieldRefInfo offset: " + reader.getOffset() + " classIndex: " + classIndex
                    + " nameAndTypeIndex: " + nameAndTypeIndex);
        }
    }

    public static class ConstantInterfaceMethodRefInfo extends ConstantMemberRefInfo {

        public ConstantInterfaceMethodRefInfo(ConstantPool constantPool) {
            this.constantPool = constantPool;
        }


        @Override
        public void readInfo(ClassReader reader) {
            this.classIndex = reader.readUint16();
            this.nameAndTypeIndex = reader.readUint16();
            logger.info("end of ConstantInterfaceMethodRefInfo offset: " + reader.getOffset()
                    + " classIndex: " + classIndex
                    + " nameAndTypeIndex: " + nameAndTypeIndex);
        }
    }

}
