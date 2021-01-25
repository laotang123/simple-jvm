package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/25 16:04
 * @description:
 * Exceptions_attribute {
 *  u2 attribute_name_index;
 *  u4 attribute_length;
 *  u2 number_of_exceptions;
 *  u2 exception_index_table[number_of_exceptions];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class ExceptionsAttribute implements AttributeInfo {
    @Override
    public void readInfo(ClassReader reader) {

    }
}
