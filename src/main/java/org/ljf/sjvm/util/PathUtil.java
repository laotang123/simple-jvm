package org.ljf.sjvm.util;

import java.io.File;

/**
 * @author: ljf
 * @date: 2021/1/21 10:36
 * @description: 处理路径的工具类
 * @modified By：
 * @version: $ 1.0
 */
public final class PathUtil {
    private PathUtil() {
    }

    /**
     * 路径的合并，不做字符有效的检测
     *
     * @param first：根路径
     * @param more：子目录
     * @return : 拼接后的路径
     */
    public static String get(String first, String... more) {
        String path;
        if (more.length == 0) {
            path = first;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            for (String segment : more) {
                if (segment.length() > 0) {
                    if (sb.length() > 0)
                        sb.append(File.separator);
                    sb.append(segment);
                }
            }
            path = sb.toString();
        }
        return path;
    }
}
