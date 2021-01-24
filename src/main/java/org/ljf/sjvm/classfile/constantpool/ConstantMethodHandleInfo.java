package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/23 17:06
 * @description: CONSTANT_MethodHandle_info {
 * u1 tag;
 * u1 reference_kind;
 * u2 reference_index;
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantMethodHandleInfo implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(ConstantMethodHandleInfo.class);

    private short referenceKind;
    private int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
        logger.info("end of ConstantMethodHandleInfo offset: " + reader.getOffset()
                + " referenceKind: " + referenceKind + "referenceIndex" + referenceIndex);
    }

    public short getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }
}
