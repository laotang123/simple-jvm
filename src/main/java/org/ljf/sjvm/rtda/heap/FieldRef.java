package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.constantpool.ConstantMemberRefInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 13:11
 * @description: 属性值的引用
 * @modified By：
 * @version: $ 1.0
 */
public class FieldRef extends MemberRef {
    private Field field;

    public FieldRef(ConstantPool constantPool, ConstantMemberRefInfo.ConstantFieldRefInfo refInfo) {
        this.constantPool = constantPool;
        this.copyMemberRefInfo(refInfo);
    }

    /**
     * 如果类D想通过字段符号引用访问类C的某个字段，首先要解 析符号引用得到类C，然后根据字段名和描述符查找字段。如果字
     * 段查找失败，则虚拟机抛出NoSuchFieldError异常。如果查找成功， 但D没有足够的权限访问该字段，
     * 则虚拟机抛出IllegalAccessError异 常。
     * 参考: jvm规范5.4.3.2
     */
    public Field resolvedField() {
        if (this.field == null) {
            this.resolveFieldRef();
        }
        return this.field;
    }

    public void resolveFieldRef() {
        Class clazzD = this.constantPool.getClazz();
        Class clazzC = this.resolvedClass();
        Field field = lookupField(clazzC, this.name, this.descriptor);
        if (field == null) {
            throw new NoSuchMethodError();
        }

        if (!field.isAccessibleTo(clazzD)) {
            throw new IllegalAccessError();
        }

        this.field = field;
    }

    private Field lookupField(Class clazz, String name, String descriptor) {
        for (Field field : clazz.getFields()) {
            if (field.name.equals(name) && field.descriptor.equals(descriptor)) {
                return field;
            }

        }

        for (Class anInterface : clazz.getInterfaces()) {
            Field field = lookupField(anInterface, name, descriptor);
            if (field != null) {
                return field;
            }
        }

        if (clazz.getSuperClass() != null) {
            return lookupField(clazz.getSuperClass(), name, descriptor);
        }

        return null;
    }

}
