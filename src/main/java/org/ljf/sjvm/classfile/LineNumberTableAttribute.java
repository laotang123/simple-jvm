package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/25 16:06
 * @description:
 * LineNumberTable_attribute {
 *  u2 attribute_name_index;
 *  u4 attribute_length;
 *  u2 line_number_table_length;
 *  { u2 start_pc;
 *  u2 line_number;
 *  } line_number_table[line_number_table_length];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class LineNumberTableAttribute implements AttributeInfo {
    @Override
    public void readInfo(ClassReader reader) {

    }
}
