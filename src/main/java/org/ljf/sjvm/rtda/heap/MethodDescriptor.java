package org.ljf.sjvm.rtda.heap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author: ljf
 * @date: 2021/2/5 11:18
 * @description: 方法参数实例
 * @modified By：
 * @version: $ 1.0
 */
public class MethodDescriptor {
    private static final Logger logger = LoggerFactory.getLogger(MethodDescriptor.class);

    private final ArrayList<String> parameterTypes = new ArrayList<>();
    private String returnType;

    public void addParameterType(String parameterType) {
        System.out.println("add parameter type: " + parameterType);
        parameterTypes.add(parameterType);
    }

    public String[] getParameterTypes() {
        String[] buffer = new String[parameterTypes.size()];
        return parameterTypes.toArray(buffer);
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
