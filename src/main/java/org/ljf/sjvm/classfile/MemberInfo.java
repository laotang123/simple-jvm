package org.ljf.sjvm.classfile;

import org.ljf.sjvm.classfile.attributes.AttributeInfo;
import org.ljf.sjvm.classfile.constantpool.ConstantPool;

/**
 * @author: ljf
 * @date: 2021/1/23 13:19
 * @description: field_info {
 * u2 access_flags;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * method_info {
 * u2 access_flags;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * @modified By:
 * @version: $ 1.0
 */
public final class MemberInfo {
    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    //    private int attributesCount;
    private AttributeInfo[] attributes;
    private ConstantPool constantPool;
//    private ClassReader reader;


    private MemberInfo() {
    }

    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int memberCount = reader.readUint16();
        MemberInfo[] members = new MemberInfo[memberCount];

        for (int i = 0; i < memberCount; i++) {
            members[i] = readMember(reader, constantPool);
        }
        return members;
    }

    private static MemberInfo readMember(ClassReader reader, ConstantPool constantPool) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.constantPool = constantPool;
        memberInfo.accessFlags = reader.readUint16();
        memberInfo.nameIndex = reader.readUint16();
        memberInfo.descriptorIndex = reader.readUint16();
        memberInfo.attributes = AttributeInfo.readAttributes(reader, constantPool);
        return memberInfo;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    /**
     * 查询常量池中的utf8找到对应的类名
     *
     * @return ： 类名
     */
    public String getName() {
        return constantPool.getUtf8(this.nameIndex);
    }

    /**
     * 获取类属性或方法的描述
     *
     * @return ： 字符串
     */
    public String getDescriptor() {
        return constantPool.getUtf8(this.descriptorIndex);
    }


}
