package org.ljf.sjvm.exceptions;

/**
 * @author: ljf
 * @date: 2021/1/31 12:13
 * @description: 方法或指令未实现异常
 * @modified By:
 * @version: $ 1.0
 */
public class UnsupportedException extends BaseException{

    public UnsupportedException() {
        super();
    }

    public UnsupportedException(String message) {
        super(message);
    }
}
