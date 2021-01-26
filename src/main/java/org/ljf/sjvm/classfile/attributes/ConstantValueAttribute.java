package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/25 16:00
 * @description: ConstantValue_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 constantvalue_index;
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class ConstantValueAttribute implements AttributeInfo {
    private int constantValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }


    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
