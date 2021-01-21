package org.ljf.sjvm.classpath;


/**
 * @author: ljf
 * @date: 2021/1/19 13:52
 * @description: 读取不同来源java文件的接口类
 * @modified By：
 * @version: $ 1.0
 */
public interface Entry {
    /**
     * 读取class文件
     *
     * @param classPath：class文件路径
     * @return 字节数组
     */
    byte[] readClass(String classPath);

    /**
     * 重写object的toString方法
     */
    String toString();
}
