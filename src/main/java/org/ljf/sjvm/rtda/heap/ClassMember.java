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

    public void copyMemberInfo(MemberInfo memberInfo){
        this.accessFlags = memberInfo.getAccessFlags();
        this.name = memberInfo.getName();
        this.descriptor = memberInfo.getDescriptor();
    }
}
