package org.ljf.sjvm.classpath;

import org.ljf.sjvm.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author: ljf
 * @date: 2021/1/20 7:30
 * @description: zip压缩class文件的读取
 * @modified By:
 * @version: $ 1.0
 */
public class ClassZipEntry implements Entry {
    private static final Logger logger = LoggerFactory.getLogger(ClassZipEntry.class);
    private final String absolutePath;

    public ClassZipEntry(String classPath) {
        this.absolutePath = IOUtil.abs(classPath);
    }

    /**
     * 读取zip或jar格式的class文件
     * 1.打开zip报错，
     * 2.打开zip成功，遍历zip文件，访问到对应文件名的class文件
     *
     * @param classPath：文件名字
     * @return ：字节数组
     */
    @Override
    public byte[] readClass(String classPath) {
        ZipFile zf = null;
        byte[] result = null;
        InputStream inputStream = null;
        try {
//            String classPath = IOUtil.getClassPath(className);
            zf = new ZipFile(this.absolutePath);
            Enumeration<? extends ZipEntry> entries = zf.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                if (zipEntry.getName().equals(classPath)) {
                    System.out.println(zipEntry);
                    inputStream = zf.getInputStream(zipEntry);
                    result = IOUtil.readFromInputStream(inputStream);
                    logger.info("loaded " + classPath + " from " + this);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(inputStream);
            IOUtil.close(zf);
        }
        return result;
    }

    @Override
    public String toString() {
        return "ZipEntry{" +
                "absolutePath='" + absolutePath + '\'' +
                '}';
    }
}
