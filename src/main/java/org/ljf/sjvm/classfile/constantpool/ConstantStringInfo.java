package org.ljf.sjvm.classfile.constantpool;

import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/22 11:05
 * @description: CONSTANT_String_info {
 * u1 tag;
 * u2 string_index;
 * }
 * @modified By：
 * @version: $ 1.0
 */
public final class ConstantStringInfo implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(ConstantNameAndTypeInfo.class);

    private int stringIndex;
    private final ConstantPool constantPool;

    ConstantStringInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }


    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
        logger.info("end of ConstantStringInfo offset: " + reader.getOffset() + " stringIndex: " + stringIndex);
    }

    @Override
    public String toString() {
        return this.constantPool.getUtf8(this.stringIndex);
    }

    public String getValue(){
        return this.constantPool.getUtf8(this.stringIndex);
    }
}
