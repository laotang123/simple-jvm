package org.ljf.sjvm.book.ch02.classpath;

/**
 * @author: ljf
 * @date: 2021/1/20 7:07
 * @description: 复合class
 * @modified By:
 * @version: $ 1.0
 */
public class CompositeEntry implements Entry {
    public CompositeEntry(String classPath) {
    }

    @Override
    public byte[] readClass(String className) {
        return new byte[0];
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
