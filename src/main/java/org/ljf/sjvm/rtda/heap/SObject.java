package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.rtda.Slot;

/**
 * @author: ljf
 * @date: 2021/1/27 11:37
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class SObject {
    protected final Class clazz;
    //    private final Slots fields;
    protected final Object data; //slots for Object, []int32 for int[] ...


//    public SObject(Class clazz, Slots fields) {
//        this.clazz = clazz;
//        this.fields = fields;
//    }
    //TODO: 将SObjectArray统一为SObject。数组对象的属性值是对象，对象的属性值是基本类型和对象(也可以统一成对象)。
    public SObject(Class clazz) {
        this.clazz = clazz;
        this.data = new Slots(clazz.getInstanceSlotCount());
//        this.data = new Object[clazz.getInstanceSlotCount()];
    }

    public Class getClazz() {
        return clazz;
    }

    public Slots getFields() {
        return (Slots) data;
    }

    public void setInt(int slotId, int value) {
        ((Slots)(this.data)).setInt(slotId, value);
    }


    public void setLong(int slotId, long value) {
        ((Slots)(this.data)).setLong(slotId, value);
    }


    public void setFloat(int slotId, float value) {
        ((Slots)(this.data)).setFloat(slotId, value);
    }

    public void setDouble(int slotId, double value) {
        ((Slots)(this.data)).setDouble(slotId, value);
    }

    public void setRef(int slotId, SObject value) {
        ((Slots)(this.data)).setRef(slotId, value);
    }

    public boolean isInstanceof(Class clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }
}
