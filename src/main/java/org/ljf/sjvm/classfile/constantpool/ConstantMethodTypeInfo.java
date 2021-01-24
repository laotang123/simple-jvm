package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/23 17:06
 * @description: CONSTANT_MethodType_info {
 * u1 tag;
 * u2 descriptor_index;
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantMethodTypeInfo implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(ConstantMethodHandleInfo.class);
    private int descriptorIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
        logger.info("end of ConstantMethodTypeInfo offset: " + reader.getOffset() +
                " descriptorIndex: " + descriptorIndex);
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
