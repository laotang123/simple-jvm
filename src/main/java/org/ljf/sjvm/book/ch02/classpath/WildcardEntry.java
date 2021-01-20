package org.ljf.sjvm.book.ch02.classpath;

import org.ljf.sjvm.book.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/20 7:21
 * @description: 通配符类型的class读取
 * @modified By:
 * @version: $ 1.0
 */
public class WildcardEntry implements Entry {
    private static final Logger logger = LoggerFactory.getLogger(WildcardEntry.class);

    private static final String CLASS = "class";
    private static final String JAR = "jar";
    private final Entry compositeEntry;

    public WildcardEntry(String contextPath) {
        String baseDir = contextPath.substring(0, contextPath.length() - 2);
        String filePaths = walkFunc(baseDir);
        this.compositeEntry = EntryFactory.newEntry(filePaths);
    }

    public String walkFunc(String walkPath) {
        File walkFile = new File(walkPath);
        boolean hasParentPath = false;//通配符匹配到class文件时将当前工作路径加入

        StringBuilder stringBuilder = new StringBuilder();
        File[] listFiles = walkFile.listFiles();
        String suffix;
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    suffix = IOUtil.getSuffix(file);
                    if (!hasParentPath && suffix.equals(CLASS)) {//class文件只添加一次当前工作路径
                        stringBuilder.append(file.getParent()).append(File.pathSeparator);
                        hasParentPath = true;
                    } else if (suffix.equalsIgnoreCase(JAR)) {//jar文件添加全路径
                        stringBuilder.append(file.getPath()).append(File.pathSeparator);
                    }
                    logger.info("wildcard entry add file: " + file.getPath());
                }
            }
        }

        //空路径填充为当前工作路径
        String filePaths = stringBuilder.toString();
        if (filePaths.isEmpty()) {
            filePaths = IOUtil.abs(walkPath);
        } else {
            filePaths = filePaths.substring(0, filePaths.length() - 1);//去除最后一个';'
        }
        return filePaths;
    }

    @Override
    public byte[] readClass(String className) {
        return compositeEntry.readClass(className);
    }

    @Override
    public String toString() {
        return "WildcardEntry{" +
                "compositeEntry=" + compositeEntry +
                '}';
    }
}
