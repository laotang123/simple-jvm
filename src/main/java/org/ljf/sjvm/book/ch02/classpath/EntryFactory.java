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
     * @param contextPath：class文件路径
     * @return ：具体的实现类实例
     */
    public static Entry newEntry(String contextPath) {
        if (contextPath.contains(File.pathSeparator)) {
            return new CompositeEntry(contextPath);
        }

        //防止异常数据，增强程序健壮性
        int length = contextPath.length();
        if (length >= 2) {
            if (contextPath.charAt(length - 1) == '*') {
                return new WildcardEntry(contextPath);
            }


        }
        if (length > 4) {
            String suffix = contextPath.substring(length - 4);
            if (suffix.equals(".zip") || suffix.equals(".ZIP") || suffix.equals(".jar") || suffix.equals(".JAR")) {
                return new ClassZipEntry(contextPath);
            }
        }

        return new DirEntry(contextPath);
    }
}
