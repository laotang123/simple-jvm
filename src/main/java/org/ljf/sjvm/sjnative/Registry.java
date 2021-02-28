package org.ljf.sjvm.sjnative;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2021/2/26 8:08
 * @description: 本地方法注册和查找
 * @modified By:
 * @version: $ 1.0
 */
public class Registry {
    private static final Map<String, NativeMethod> registry = new HashMap<>();

    /**
     * 类名，方法名和方法描述符唯一确定一个本地方法
     */
    public static void register(String className, String methodName, String methodDescriptor, NativeMethod method) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        registry.put(key, method);
    }

    public static NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        if (methodDescriptor.equals("()V") && methodName.equals("registerNatives")) {
            return new EmptyNativeMethod();
        }
        return registry.getOrDefault(key, null);
    }
}
