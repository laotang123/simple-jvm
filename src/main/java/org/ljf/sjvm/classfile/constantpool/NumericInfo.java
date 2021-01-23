package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;

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
    static class ConstantIntegerInfo extends NumericInfo {
        private int value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readInt32();
        }

        public int getValue() {
            return value;
        }
    }

    static class ConstantLongInfo extends NumericInfo {
        private long value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readInt64();
        }

        public long getValue() {
            return value;
        }
    }

    static class ConstantFloatInfo extends NumericInfo {
        private float value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readFloat();
        }

        public float getValue() {
            return value;
        }
    }

    static class ConstantDoubleInfo extends NumericInfo {
        private double value;

        @Override
        public void readInfo(ClassReader reader) {
            this.value = reader.readDouble();
        }

        public double getValue() {
            return value;
        }
    }

}
