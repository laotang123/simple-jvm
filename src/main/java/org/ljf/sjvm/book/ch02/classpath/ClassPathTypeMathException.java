package org.ljf.sjvm.book.ch02.classpath;

/**
 * @author: ljf
 * @date: 2021/1/20 7:38
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class ClassPathTypeMathException extends Exception {
    public ClassPathTypeMathException() {
        super();
    }

    public ClassPathTypeMathException(String message) {
        super(message);
    }

    public ClassPathTypeMathException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassPathTypeMathException(Throwable cause) {
        super(cause);
    }

    protected ClassPathTypeMathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
