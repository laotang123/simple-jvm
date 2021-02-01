package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.constantpool.ConstantMemberRefInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 13:24
 * @description: 接口方法引用
 * @modified By：
 * @version: $ 1.0
 */
public class InterfaceMethodRef extends MemberRef {
    private Method method;

    public InterfaceMethodRef(ConstantPool constantPool, ConstantMemberRefInfo.ConstantInterfaceMethodRefInfo refInfo) {
        this.constantPool = constantPool;
        this.copyMemberRefInfo(refInfo);
    }
}
