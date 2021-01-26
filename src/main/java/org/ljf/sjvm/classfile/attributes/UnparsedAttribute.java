package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/25 16:09
 * @description: java虚拟机不识别属性
 * @modified By：
 * @version: $ 1.0
 */
public class UnparsedAttribute implements AttributeInfo {
    private final String name;
    private final long length;//uint32
    private byte[] info;

    public UnparsedAttribute(String attrName, long attrLen) {
        this.name = attrName;
        this.length = attrLen;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length);
    }

    public byte[] getInfo() {
        return this.info;
    }

    public String getName() {
        return name;
    }
}
