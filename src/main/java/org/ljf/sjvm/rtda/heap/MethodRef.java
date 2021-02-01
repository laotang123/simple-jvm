package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.constantpool.ConstantMemberRefInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 13:21
 * @description: 方法引用
 * @modified By：
 * @version: $ 1.0
 */
public class MethodRef extends MemberRef{
    private Method method;

    public MethodRef(ConstantPool constantPool, ConstantMemberRefInfo.ConstantMethodRefInfo refInfo){
        this.constantPool = constantPool;
        this.copyMemberRefInfo(refInfo);
    }
}
