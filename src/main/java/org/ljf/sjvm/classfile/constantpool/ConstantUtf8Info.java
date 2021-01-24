package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.ljf.sjvm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author: ljf
 * @date: 2021/1/23 13:38
 * @description: utf8常量
 * CONSTANT_Utf8_info {
 * u1 tag;
 * u2 length;
 * u1 bytes[length];
 * }
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantUtf8Info implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(ConstantUtf8Info.class);
    private int length;
    private String str;


    @Override
    public String toString() {
        return str;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.length = reader.readUint16();
        byte[] bytes = reader.readBytes(length);
        str = ByteUtils.decodeMUTF8(bytes);
        logger.info("end of ConstantUtf8Info offset: " + reader.getOffset() + " length: " + length + " value: " + str);
        if (str == null) {
            logger.error("read utf8 info error");
        }
    }
}
