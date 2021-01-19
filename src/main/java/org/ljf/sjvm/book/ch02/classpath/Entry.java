package org.ljf.sjvm.book.ch02.classpath;

/**
 * @author: ljf
 * @date: 2021/1/19 13:52
 * @description: 读取不同来源java文件的接口类
 * @modified By：
 * @version: $ 1.0
 */
public interface Entry {
    byte[] readClass(String className) throws ClassNotFoundException;

    /**
     * 重写object的toString方法
     */
    String toString();
}
