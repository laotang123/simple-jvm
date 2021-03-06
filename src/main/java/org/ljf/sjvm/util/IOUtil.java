package org.ljf.sjvm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author: ljf
 * @date: 2021/1/20 9:47
 * @description: io 工具类
 * @modified By：
 * @version: $ 1.0
 */
public class IOUtil {
    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);
    public static final String CURRENT_PATH = "./";

    public static String abs(String path) {
        if (new File(path).isAbsolute()) {
            return path;
        } else {
            String prefix = System.getProperty("user.dir");
            return prefix.concat(File.separator + path);
        }
    }

    public static String dotToSlash(String name) {
        return name.replace('.', '/');
    }

    public static String getClassPath(String className) {
        return className.replace('.', '/').concat(".class");
    }

    public static byte[] readFromInputStream(InputStream fis) {
        //大多数class文件在1kb左右
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        int count;
        //带缓冲读数据
        try {
            while ((count = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getSuffix(File file) {
        String fileName = file.getName();
        logger.info("file name: " + fileName + " call getSuffix method");
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
