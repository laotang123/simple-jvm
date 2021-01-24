package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/22 11:05
 * @description: CONSTANT_Class_info {
 * u1 tag;
 * u2 name_index;
 * }
 * @modified By：
 * @version: $ 1.0
 */
public final class ConstantClassInfo implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(ConstantClassInfo.class);

    private int nameIndex;
    private final ConstantPool constantPool;

    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getName() {
        return this.constantPool.getUtf8(nameIndex);
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        logger.info("end of ConstantClassInfo offset: " + reader.getOffset() + " nameIndex: " + nameIndex);
    }
}
