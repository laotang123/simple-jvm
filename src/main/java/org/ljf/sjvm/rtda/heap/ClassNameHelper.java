package org.ljf.sjvm.rtda.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2021/2/21 14:02
 * @description: 处理类名的工具类
 * @modified By：
 * @version: $ 1.0
 */
public class ClassNameHelper {
    public static final Map<String, String> primitiveTypes = new HashMap<>();

    static {
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

    /**
     * [XXX -> [[XXX
     * int -> [I
     * XXX -> [LXXX
     */
    public static String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    private static String toDescriptor(String className) {
        if (className.charAt(0) == '[') {
            //array
            return className;
        }

        String primitiveType = primitiveTypes.get(className);
        if (primitiveType != null) {
            //primitive
            return primitiveType;
        }

        //object
        return "L" + className + ";";
    }

    /**
     * [[XXX -> [XXX
     * [LXXX; -> XXX
     * [I -> int
     */
    public static String getComponentClassName(String className) {
        if (className.charAt(0) == '[') {
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new IllegalArgumentException("not array: " + className);
    }

    private static String toClassName(String descriptor) {
        if (descriptor.charAt(0) == '[') {
            //array
            return descriptor;
        }

        if (descriptor.charAt(0) == 'L') {
            //object
            return descriptor.substring(1, descriptor.length() - 1);
        }

        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (descriptor.equals(entry.getValue())) {
                //primitive
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Invalid descriptor: " + descriptor);
    }
}
