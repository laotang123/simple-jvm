package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/23 17:06
 * @description: CONSTANT_MethodType_info {
 * u1 tag;
 * u2 descriptor_index;
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantMethodTypeInfo implements ConstantInfo {
    private int descriptorIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
