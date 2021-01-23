package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/23 13:35
 * @description: CONSTANT_NameAndType_info {
 * u1 tag;
 * u2 name_index;
 * u2 descriptor_index;
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantNameAndTypeInfo implements ConstantInfo {
    private int nameIndex;
    private int descriptorIndex;

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }
}
