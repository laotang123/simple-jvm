package org.ljf.sjvm.book.ch02.classpath;

import java.io.*;

/**
 * @author: ljf
 * @date: 2021/1/20 7:08
 * @description: 基于目录的class读取
 * @modified By:
 * @version: $ 1.0
 */
public class DirEntry implements Entry {
    private String absolutePath;

    public DirEntry(String classPath) throws ClassPathTypeMathException {
        if (new File(classPath).isAbsolute()) {
            this.absolutePath = classPath;
        } else throw new ClassPathTypeMathException(classPath + " is not absolute path");

    }

    @Override
    public byte[] readClass(String className) {
        String fileName = absolutePath.concat(File.separator + className);
        FileInputStream fis = null;
        byte[] result = null;
        try {
            File file = new File(fileName);
            result = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
