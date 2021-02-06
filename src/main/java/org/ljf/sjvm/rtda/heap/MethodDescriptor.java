package org.ljf.sjvm.rtda.heap;

import java.util.ArrayList;

/**
 * @author: ljf
 * @date: 2021/2/5 11:18
 * @description: 方法参数实例
 * @modified By：
 * @version: $ 1.0
 */
public class MethodDescriptor {

    private ArrayList<String> parameterTypes;
    private String returnType;

    public void addParameterType(String parameterType) {
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
