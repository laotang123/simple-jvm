package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/23 17:06
 * @description: CONSTANT_InvokeDynamic_info {
 * u1 tag;
 * u2 bootstrap_method_attr_index;
 * u2 name_and_type_index;
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(ConstantInvokeDynamicInfo.class);
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;


    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
        logger.info("end of ConstantInvokeDynamicInfo offset: " + reader.getOffset() +
                " bootstrapMethodAttrIndex: " + bootstrapMethodAttrIndex
                + " nameAndTypeIndex" + nameAndTypeIndex);
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
