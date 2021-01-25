package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/25 16:06
 * @description:
 * LocalVariableTable_attribute {
 *  u2 attribute_name_index;
 *  u4 attribute_length;
 *  u2 local_variable_table_length;
 *  { u2 start_pc;
 *  u2 length;
 *  u2 name_index;
 *  u2 descriptor_index;
 *  u2 index;
 *  } local_variable_table[local_variable_table_length];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class LocalVariableTableAttribute implements AttributeInfo {
    @Override
    public void readInfo(ClassReader reader) {

    }
}
