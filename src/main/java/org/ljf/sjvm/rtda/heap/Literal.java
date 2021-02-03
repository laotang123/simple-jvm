package org.ljf.sjvm.rtda.heap;

/**
 * @author: ljf
 * @date: 2021/2/1 11:13
 * @description: 字面量：整数，浮点数和字符串字面量
 * @modified By：
 * @version: $ 1.0
 */
public abstract class Literal implements Constant {

    public static class IntegerLiteral extends Literal {
        private final int value;

        public IntegerLiteral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public static class LongLiteral extends Literal {
        private final long value;

        public LongLiteral(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

    }

    public static class FloatLiteral extends Literal {
        private final float value;

        public FloatLiteral(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }

    }

    public  static class DoubleLiteral extends Literal {
        private final double value;

        public DoubleLiteral(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

    }


    public static class StringLiteral extends Literal {
        private final String value;

        public StringLiteral(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
