package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/22 7:58
 * @description: class文件结构
 * ClassFile {
 *  u4 magic;
 *  u2 minor_version;
 *  u2 major_version;
 *  u2 constant_pool_count;
 *  cp_info constant_pool[constant_pool_count-1];
 *  u2 access_flags;
 *  u2 this_class;
 *  u2 super_class;
 *  u2 interfaces_count;
 *  u2 interfaces[interfaces_count];
 *  u2 fields_count;
 *  field_info fields[fields_count];
 *  u2 methods_count;
 *  method_info methods[methods_count];
 *  u2 attributes_count;
 *  attribute_info attributes[attributes_count];
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ClassFile {
    //long magic //uint32
    int minorVersion;//uint16 u2
    int majorVersion; //uint16 u2
    ConstantPool constantPool;
    int accessFlags; //uint16 u2
    int thisClass; //uint16 u2
    int superClass; //uint16 u2
    int[] interfaces; //uint16[] u2
    MemberInfo[] fields;
    MemberInfo[] methods;
    AttributeInfo[] attributes;

}
