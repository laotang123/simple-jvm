package org.ljf.sjvm.classpath;

import org.apache.commons.lang3.StringUtils;
import org.ljf.sjvm.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.dc.path.PathException;

import java.io.File;

/**
 * @author: ljf
 * @date: 2021/1/21 7:52
 * @description: simple-jvm 类路径加载的顺序
 * @modified By:
 * @version: $ 1.0
 */
public class ClassPathContainer {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathContainer.class);
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
    public static ClassPathContainer parse(String jreOption, String cpOption) {
        ClassPathContainer classPath = new ClassPathContainer();
        try {
            parseBootAndExtClassPath(jreOption, classPath);
        } catch (PathException e) {
            logger.error(e.getMessage());
            return null;
        }
        parseUserClassPath(cpOption, classPath);
        return classPath;
    }

    /**
     * 用户class path 默认为当前路径
     *
     * @param cpOption：class path
     */
    private static void parseUserClassPath(String cpOption, ClassPathContainer classPath) {
        if (StringUtils.isEmpty(cpOption)) {
            cpOption = "";
        }
        classPath.userClassPath = EntryFactory.newEntry(cpOption);
    }

    private static void parseBootAndExtClassPath(String jreOption, ClassPathContainer classPath) throws PathException {
        String jreDir = getJreDir(jreOption);

        // jre/lib/*
        String jreLibPath = PathUtil.get(jreDir, "lib", "*");
        classPath.bootClassPath = EntryFactory.newEntry(jreLibPath);

        // jre/lib/ext/*
        String jreExtPath = PathUtil.get(jreDir, "lib", "ext", "*");
        classPath.extClassPath = EntryFactory.newEntry(jreExtPath);
    }

    /**
     * 1.判断jre路径是否存在
     * 2.不存在从当前路径找
     * 3.当前路径找不到再获取javaHome环境变量
     *
     * @param jreOption：java运行时环境
     * @return ：jre路径
     */
    private static String getJreDir(String jreOption) throws PathException {
        if (!StringUtils.isEmpty(jreOption) && new File(jreOption).exists()) {
            return jreOption;
        } else if (new File("./jre").exists()) {
            return "./jre";
        } else if (!System.getenv("JAVA_HOME").isEmpty()) {
            return System.getenv("JAVA_HOME").concat("/jre");
        } else {
            throw new PathException("jre path" + jreOption + " not found");
        }

    }

    /**
     * 读取指定类名的字节流；顺序：
     * 1.bootClassPath
     * 2.extClassPath
     * 3.userClassPath
     *
     * @param className：类名
     * @return ：字节流，或者为null(文件不存在或其他异常)
     */
    public byte[] readClass(String className) throws ClassNotFoundException {
        byte[] result;
        if ((result = bootClassPath.readClass(className)) != null) {
            return result;
        } else if ((result = extClassPath.readClass(className)) != null) {
            return result;
        } else if ((result = userClassPath.readClass(className)) != null) {
            return result;
        } else {
            throw new ClassNotFoundException(className);
        }


    }

    public String toString() {
        return userClassPath.toString();
    }
}
