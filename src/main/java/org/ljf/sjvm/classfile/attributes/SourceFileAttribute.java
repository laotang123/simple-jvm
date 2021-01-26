package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;
import org.ljf.sjvm.classfile.constantpool.ConstantPool;

/**
 * @author: ljf
 * @date: 2021/1/25 16:07
 * @description: SourceFile_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 sourcefile_index;
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class SourceFileAttribute implements AttributeInfo {
    private int sourceFileIndex;
    private final ConstantPool constantPool;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    public String getSourceFileName() {
        return this.constantPool.getUtf8(sourceFileIndex);
    }
}
