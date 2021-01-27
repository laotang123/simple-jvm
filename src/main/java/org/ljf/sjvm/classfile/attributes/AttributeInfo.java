package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;
import org.ljf.sjvm.classfile.constantpool.ConstantPool;

/**
 * @author: ljf
 * @date: 2021/1/22 8:11
 * @description: 属性表中的单个元素
 * attribute_info {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u1 info[attribute_length];
 * }
 * @modified By:
 * @version: $ 1.0
 */
public interface AttributeInfo {
    void readInfo(ClassReader reader);

    static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attrsCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attrsCount];

        for (int i = 0; i < attrsCount; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        //attribute共有属性值attribute_name_index attribute_length，外循环统一读
        int attrNameIndex = reader.readUint16();
        String attrName = constantPool.getUtf8(attrNameIndex);
        long attrLen = reader.readUint32();
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen, constantPool);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    static AttributeInfo newAttributeInfo(String attrName, long attrLen, ConstantPool constantPool) {
        switch (attrName) {
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecateAttribute();
            case "Exceptions":
                return new ExceptionsAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable":
                return new LocalVariableTypeTableAttribute();
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            case "Synthetic":
                return new SyntheticAttribute();
            case "InnerClasses":
                return new InnerClassAttribute();
            case "Signature":
                return new SignatureAttribute(constantPool);
            case "BootstrapMethods":
                return new BootstrapMethodsAttribute();
            case "EnclosingMethod":
                return new EnclosingMethod(constantPool);
            default:
                return new UnparsedAttribute(attrName, attrLen);
        }
    }


}
