package org.ljf.sjvm.book.ch02.classpath;

import java.io.File;

/**
 * @author: ljf
 * @date: 2021/1/20 7:00
 * @description: entry创建使用工厂方法
 * @modified By:
 * @version: $ 1.0
 */
public class EntryFactory {

    /**
     * 工厂方法创建实例entry
     *
     * @param classPath：class文件路径
     * @return ：具体的实现类实例
     */
    public static Entry newEntry(String classPath) {
        if (classPath.contains(File.separator)) {
            return new CompositeEntry(classPath);
        }

        if (classPath.charAt(classPath.length() - 1) == '*') {
            return new WildcardEntry(classPath);
        }

        String suffix = classPath.substring(classPath.length() - 4);
        if (suffix.equals(".zip") || suffix.equals(".ZIP") || suffix.equals(".jar") || suffix.equals(".JAR")) {
            return new ZipEntry(classPath);
        }

        Entry entry = null;
        try {
            entry = new DirEntry(classPath);
        } catch (ClassPathTypeMathException e) {
            e.printStackTrace();
        }

        return entry;
    }
}
