package org.ljf.sjvm.rtda.heap;

/**
 * @author: ljf
 * @date: 2021/2/21 11:10
 * @description: 数组对象
 * @modified By：
 * @version: $ 1.0
 */
public class ArraySObject extends SObject{
    protected final int len;

    public ArraySObject(Class clazz,int len){
        super(clazz);
        this.len = len;
    }

    @Override
    public String toString() {
        return "ArraySObject{" +
                "len=" + len +
                '}';
    }
}
