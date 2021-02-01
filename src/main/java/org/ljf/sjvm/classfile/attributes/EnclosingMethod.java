package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;
import org.ljf.sjvm.classfile.constantpool.ConstantPool;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/27 10:20
 * @description: EnclosingMethod_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 class_index;
 * u2 method_index;
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class EnclosingMethod implements AttributeInfo {
    private int classIndex;
    private int methodIndex;
    private final ConstantPool constantPool;

    public EnclosingMethod(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.methodIndex = reader.readUint16();
    }

    public String getClassName() {
        return this.constantPool.getClassName(classIndex);
    }

    public String getMethodNameAndDescriptor() {
        if (this.methodIndex > 0) {
            return Arrays.toString(this.constantPool.getNameAndType(methodIndex));
        }
        return "\t;\t";

    }
}
