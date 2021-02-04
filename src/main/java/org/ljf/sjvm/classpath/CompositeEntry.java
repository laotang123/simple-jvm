package org.ljf.sjvm.classpath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/20 7:07
 * @description: 复合class
 * @modified By:
 * @version: $ 1.0
 */
public class CompositeEntry implements Entry {
    private static final Logger logger = LoggerFactory.getLogger(CompositeEntry.class);
    private final Entry[] entries;

    public CompositeEntry(String contextPathList) {
        String[] pathList = contextPathList.split(File.pathSeparator);
        int length = pathList.length;
        entries = new Entry[length];
        for (int i = 0; i < length; i++) {
            entries[i] = EntryFactory.newEntry(pathList[i]);
        }
    }

    /**
     * 从多个entry中读取单个class文件流
     *
     * @param classPath：文件名字
     * @return ：字节数组
     */
    @Override
    public byte[] readClass(String classPath) {
        byte[] result = null;
        for (Entry entry : entries) {
            result = entry.readClass(classPath);
            if (result != null) {
//                logger.info("loaded " + classPath + " from " + this);
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "CompositeEntry{" +
                "entries=" + Arrays.toString(entries) +
                '}';
    }
}
