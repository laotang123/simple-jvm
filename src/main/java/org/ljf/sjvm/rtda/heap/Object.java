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

    public void setInt(int slotId, int value) {
        this.fields.setInt(slotId, value);
    }

    public void setLong(int slotId, long value) {
        this.fields.setLong(slotId, value);
    }


    public void setFloat(int slotId, float value) {
        this.fields.setFloat(slotId, value);
    }

    public void setDouble(int slotId, double value) {
        this.fields.setDouble(slotId, value);
    }

    public void setRef(int slotId, Object value) {
        this.fields.setRef(slotId, value);
    }

    public boolean isInstanceof(Class clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }
}
