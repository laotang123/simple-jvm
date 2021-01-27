package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;
import org.ljf.sjvm.classfile.constantpool.ConstantPool;

/**
 * @author: ljf
 * @date: 2021/1/27 10:00
 * @description: Signature_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 signature_index;
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class SignatureAttribute implements AttributeInfo {
    private int signatureIndex;
    private final ConstantPool constantPool;

    public SignatureAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.signatureIndex = reader.readUint16();
    }

    public String getSignature() {
        return constantPool.getUtf8(signatureIndex);
    }


}
