package org.ljf.sjvm.exceptions;

/**
 * @author: ljf
 * @date: 2021/1/31 12:11
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class BaseException extends Exception{
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
