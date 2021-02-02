package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/23 11:05
 * @description: CONSTANT_Integer_info {
 * u1 tag;
 * u4 bytes;
 * }
 * CONSTANT_Float_info {
 * u1 tag;
 * u4 bytes;
 * }
 * <p>
 * CONSTANT_Long_info {
 * u1 tag;
 * u4 high_bytes;
 * u4 low_bytes;
 * }
 * CONSTANT_Double_info {
 * u1 tag;
 * u4 high_bytes;
 * u4 low_bytes;
 * }
 * @modified By:
 * @version: $ 1.0
 */
public abstract class NumericInfo implements ConstantInfo {
    private static final Logger logger = LoggerFactory.getLogger(NumericInfo.class);

    public static class ConstantIntegerInfo extends NumericInfo {
        private int value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readInt32();
            logger.info("end of ConstantIntegerInfo offset: " + reader.getOffset() + " value: " + value);
        }

        public int getValue() {
            return value;
        }
    }

    public static class ConstantLongInfo extends NumericInfo {
        private long value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readInt64();
            logger.info("end of ConstantLongInfo offset: " + reader.getOffset() + " value: " + value);
        }

        public long getValue() {
            return value;
        }
    }

    public static class ConstantFloatInfo extends NumericInfo {
        private float value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readFloat();
            logger.info("end of ConstantFloatInfo offset: " + reader.getOffset() + " value: " + value);

        }

        public float getValue() {
            return value;
        }
    }

    public static class ConstantDoubleInfo extends NumericInfo {
        private double value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readDouble();
            logger.info("end of ConstantDoubleInfo offset: " + reader.getOffset() + " value: " + value);

        }

        public double getValue() {
            return value;
        }
    }

}
