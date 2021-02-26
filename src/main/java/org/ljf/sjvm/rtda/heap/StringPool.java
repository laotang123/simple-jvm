package org.ljf.sjvm.rtda.heap;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2021/2/23 13:57
 * @description: 常量池
 * @modified By：
 * @version: $ 1.0
 */
public class StringPool {
    private static final Map<String, SObject> internedStrings = new HashMap<>();

    public static SObject stringObject(ClassLoader loader, String str) {
        SObject internedStr = internedStrings.get(str);
        if (internedStr != null) {
            return internedStr;
        }

        Character[] characters = ArrayUtils.toObject(string2Utf16(str));
        SObject jChars = new SObjectArray(loader.loadClass("[C"), characters);
        SObject jStr = loader.loadClass("java/lang/String").newObject();
        jStr.setRefVar("value", "[C", jChars);
        internedStrings.put(str, jStr);
        return jStr;
    }

    //java.lang.string(object) -> string
    public static String jString(SObject jStr) {
        SObject charArr = jStr.getRefVar("value", "[C");
        return utf162String(((SObjectArray) charArr).chars());
    }

    public static char[] string2Utf16(String s) {
        return s.toCharArray();
    }

    public static String utf162String(char[] chars) {
        return new String(chars);
    }
}
