package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.MemberInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 9:36
 * @description: 字段和方法的共有特性
 * @modified By：
 * @version: $ 1.0
 */
public class ClassMember {
    protected int accessFlags;
    protected String name;
    protected String descriptor;
    protected Class clazz;

    public void copyMemberInfo(MemberInfo memberInfo) {
        this.accessFlags = memberInfo.getAccessFlags();
        this.name = memberInfo.getName();
        this.descriptor = memberInfo.getDescriptor();
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNTHETIC);
    }


    public int getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public Class getClazz() {
        return clazz;
    }

    public boolean isAccessibleTo(Class other) {
        if (this.isPublic()) {
            return true;
        }

        Class clazz = this.clazz;
        if (this.isProtected()) {
            //other.getClass().isAssignableFrom(Class.class): 对应go语言的other.subClassOf(clazz)
            return clazz == other || other.getClass().isAssignableFrom(Class.class) ||
                    clazz.getPackageName().equals(other.getPackageName());
        }

        if (this.isPrivate()) {
            return clazz.getPackageName().equals(other.getPackageName());
        }

        return clazz == other;
    }
}
