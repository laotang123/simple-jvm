package org.ljf.sjvm.book.ch02.classpath;

/**
 * @author: ljf
 * @date: 2021/1/20 7:30
 * @description: zip压缩class文件的读取
 * @modified By:
 * @version: $ 1.0
 */
public class ZipEntry implements Entry {

    public ZipEntry(String classPath) {
    }

    @Override
    public byte[] readClass(String className) {
        return new byte[0];
    }
}
