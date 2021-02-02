package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classfile.attributes.ConstantValueAttribute;

/**
 * @author: ljf
 * @date: 2021/2/1 7:25
 * @description: class属性
 * @modified By:
 * @version: $ 1.0
 */
public class Field extends ClassMember {
    private long constValueIndex;
    private long slotId;//uint

    public static Field[] newFields(Class clazz, MemberInfo[] cfFields) {
        Field[] fields = new Field[cfFields.length];
        for (int i = 0; i < cfFields.length; i++) {
            fields[i] = new Field();
            fields[i].clazz = clazz;
            fields[i].copyMemberInfo(cfFields[i]);
        }
        return fields;
    }

    public void copyAttributes(MemberInfo cfField) {
        ConstantValueAttribute attribute = cfField.getConstantValueAttribute();
        if (attribute != null) {
            this.constValueIndex = attribute.getConstantValueIndex();
        }
    }


    public boolean IsVolatile() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VOLATILE);
    }

    public boolean IsTransient() {
        return 0 != (this.accessFlags & AccessFlags.ACC_TRANSIENT);
    }

    public boolean IsEnum() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ENUM);
    }

    public long getConstValueIndex() {
        return constValueIndex;
    }

    public long getSlotId() {
        return slotId;
    }


    public boolean isLongOrDouble() {
        return this.descriptor.equals("J") || this.descriptor.equals("D");
    }
}
