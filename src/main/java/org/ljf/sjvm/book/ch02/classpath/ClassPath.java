package org.ljf.sjvm.book.ch02.classpath;

import java.io.File;

/**
 * @author: ljf
 * @date: 2021/1/21 7:52
 * @description: simple-jvm 类路径加载的顺序
 * @modified By:
 * @version: $ 1.0
 */
public class ClassPath {
    private Entry bootClassPath;
    private Entry extClassPath;
    private Entry userClassPath;

    /**
     * 根据jre(java 运行时环境)和用户路径来解析类加载路径
     *
     * @param jreOption：java runtime environment
     * @param cpOption：class path
     * @return ：ClassPath实例
     */
    public static ClassPath parse(String jreOption, String cpOption) {
        ClassPath classPath = new ClassPath();
        parseBootAndExtClassPath(jreOption);
        parseUserClassPath(cpOption);
        return classPath;
    }

    private static void parseUserClassPath(String cpOption) {

    }

    private static void parseBootAndExtClassPath(String jreOption) {
        String jreDir = getJreDir(jreOption);
    }

    /**
     * 1.判断jre路径是否存在
     * 2.不存在从当前路径找
     * 3.当前路径找不到再获取javaHome环境变量
     *
     * @param jreOption：java运行时环境
     * @return ：jre路径
     */
    private static String getJreDir(String jreOption) {
        File jreFile = new File(jreOption);
        if (!jreOption.isEmpty() && jreFile.exists()) {
            return jreOption;
        }else if (new File("./jre").exists()){
            return "./jre";
        }
        return "";
    }
}
