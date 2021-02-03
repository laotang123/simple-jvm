package org.ljf.sjvm.rtda.heap;

/**
 * @author: ljf
 * @date: 2021/1/27 11:37
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class Object {
    private Class clazz;
    private Slots fields;


    public Object(Class clazz, Slots fields) {
        this.clazz = clazz;
        this.fields = fields;
    }

    public Class getClazz() {
        return clazz;
    }

    public Slots getFields() {
        return fields;
    }
}
