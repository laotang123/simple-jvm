package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.constantpool.ConstantMemberRefInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 13:11
 * @description: 属性值的引用
 * @modified By：
 * @version: $ 1.0
 */
public class FieldRef extends MemberRef{
    private Field field;

    public FieldRef(ConstantPool constantPool, ConstantMemberRefInfo.ConstantFieldRefInfo refInfo){
        this.constantPool = constantPool;
        this.copyMemberRefInfo(refInfo);
    }
}
