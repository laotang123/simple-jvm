package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/25 16:03
 * @description:
 * Deprecated_attribute {
 *  u2 attribute_name_index;
 *  u4 attribute_length;
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class DeprecateAttribute implements AttributeInfo {
    @Override
    public void readInfo(ClassReader reader) {

    }
}
