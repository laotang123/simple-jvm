package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.constantpool.ConstantMemberRefInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 11:43
 * @description: 属性和方法的共有字段
 * @modified By：
 * @version: $ 1.0
 */
public class MemberRef extends SymRef {
    private String name;
    private String descriptor;

    public void copyMemberRefInfo(ConstantMemberRefInfo refInfo) {
        this.className = refInfo.className();
        String[] nameAndDescriptor = refInfo.NameAndDescriptor();
        this.name = nameAndDescriptor[0];
        this.descriptor = nameAndDescriptor[1];
    }
}
