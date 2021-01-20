package org.ljf.sjvm.book.ch02.classpath;

import org.ljf.sjvm.book.util.IOUtil;

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
    private final Entry compositeEntry;

    public WildcardEntry(String contextPath) {
        String baseDir = contextPath.substring(0,contextPath.length() - 2);
//        String filePaths = walkFunc(baseDir);
        this.compositeEntry = EntryFactory.newEntry(baseDir);
    }

//    public String walkFunc(String walkPath) {
//        File walkFile = new File(walkPath);
//
//        StringBuilder stringBuilder = new StringBuilder();
//        File[] listFiles = walkFile.listFiles();
//        if (listFiles != null) {
//            for (File file : listFiles) {
//                if (file.isFile()) {
//                    stringBuilder.append(file.getParent()).append(File.pathSeparator);
//                }
//            }
//        }
//
//        //空路径填充为当前工作路径
//        String filePaths = stringBuilder.toString();
//        if (filePaths.isEmpty()){
//            filePaths = IOUtil.abs(walkPath);
//        }
//        return filePaths;
//    }

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
