package org.ljf.sjvm.book.ch02.classpath;

import org.ljf.sjvm.book.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author: ljf
 * @date: 2021/1/20 7:08
 * @description: 基于目录的class读取
 * @modified By:
 * @version: $ 1.0
 */
public class DirEntry implements Entry {
    private static final Logger logger = LoggerFactory.getLogger(DirEntry.class);
    private final String absolutePath;

    public DirEntry(String contextPath) {
        this.absolutePath = IOUtil.abs(contextPath);
    }

    @Override
    public byte[] readClass(String className) {
        String classPath = IOUtil.getClassPath(className);
        FileInputStream fis = null;
        byte[] result = null;
        try {
            fis = new FileInputStream(this.absolutePath.concat(File.separator + classPath));
            result = IOUtil.readFromInputStream(fis);
        } catch (FileNotFoundException e) {
            logger.warn(e.getMessage());
        } finally {
            IOUtil.close(fis);
        }

        return result;
    }

    @Override
    public String toString() {
        return "DirEntry{" +
                "absolutePath='" + absolutePath + '\'' +
                '}';
    }
}
