package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.constantpool.ConstantMemberRefInfo;

/**
 * @author: ljf
 * @date: 2021/2/1 13:21
 * @description: 方法引用
 * @modified By：
 * @version: $ 1.0
 */
public class MethodRef extends MemberRef {
    private Method method;

    public MethodRef(ConstantPool constantPool, ConstantMemberRefInfo.ConstantMethodRefInfo refInfo) {
        this.constantPool = constantPool;
        this.copyMemberRefInfo(refInfo);
    }

    public Method resolvedMethod() {
        if (this.method == null) {
            this.resolvedMethodRef();
        }
        return this.method;
    }

    private void resolvedMethodRef() {
        //TODO: clazzD和clazzC哪里不同？
        Class clazzD = this.constantPool.getClazz();
        Class clazzC = this.resolvedClass();

        //class d call class c method
        if (clazzC.isInterface()) {
            throw new IncompatibleClassChangeError(clazzC.getName());
        }

        Method method = lookupMethod(clazzC);

        if (method == null) {
            throw new NoSuchMethodError(this.name);
        }

        if (!method.isAccessibleTo(clazzD)) {
            throw new IllegalAccessError("method: " + this.name + " call " + clazzD.getName());
        }

        this.method = method;

    }

    private Method lookupMethod(Class clazzC) {
        Method method = MethodLookup.lookupMethodInClass(clazzC, this);
        if (method == null) {
            method = MethodLookup.lookupMethodInInterfaces(clazzC.getInterfaces(), this);
        }
        return method;
    }

}
