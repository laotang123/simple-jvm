package org.ljf.sjvm.classfile;

import org.ljf.sjvm.classfile.constantpool.ConstantPool;

/**
 * @author: ljf
 * @date: 2021/1/25 15:58
 * @description:
 * Code_attribute {
 *  u2 attribute_name_index;
 *  u4 attribute_length;
 *  u2 max_stack;
 *  u2 max_locals;
 *  u4 code_length;
 *  u1 code[code_length];
 *  u2 exception_table_length;
 *  { u2 start_pc;
 *  u2 end_pc;
 *  u2 handler_pc;
 *  u2 catch_type;
 *  } exception_table[exception_table_length];
 *  u2 attributes_count;
 *  attribute_info attributes[attributes_count];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class CodeAttribute implements AttributeInfo {
    private ConstantPool constantPool;

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {

    }
}
