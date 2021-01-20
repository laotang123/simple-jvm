package org.ljf.sjvm.book.ch02.classpath;

/**
 * @author: ljf
 * @date: 2021/1/20 7:21
 * @description: 通配符类型的class读取
 * @modified By:
 * @version: $ 1.0
 */
public class WildcardEntry implements Entry {
    public WildcardEntry(String classPath) {
    }

    @Override
    public byte[] readClass(String className) {
        return new byte[0];
    }
}
