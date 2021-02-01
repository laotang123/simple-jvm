package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.constantpool.ConstantClassInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 11:38
 * @description: 类引用
 * @modified By：
 * @version: $ 1.0
 */
public class ClassRef extends SymRef{

    public ClassRef(ConstantPool constantPool, ConstantClassInfo classInfo){
        this.constantPool = constantPool;
        this.className = classInfo.getName();
    }
}
