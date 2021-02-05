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

    public Method resolvedInterfaceMethod() {
        if (this.method == null) {
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    private void resolveInterfaceMethodRef() {
        Class clazzD = this.constantPool.getClazz();
        Class iFaceC = this.resolvedClass();


        if (!iFaceC.isInterface()) {
            throw new IncompatibleClassChangeError(iFaceC.getName());
        }

        Method method = lookupInterfaceMethod(iFaceC);

        if (method == null) {
            throw new NoSuchMethodError(this.name);
        }

        if (!method.isAccessibleTo(clazzD)) {
            throw new IllegalAccessError("method: " + method.getName() + " call class: " + clazzD.getName());
        }

        this.method = method;
    }

    private Method lookupInterfaceMethod(Class iFaceC) {
        for (Method method : iFaceC.getMethods()) {
            if (MethodLookup.equalMethod(method, this)) {
                return method;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(iFaceC.getInterfaces(), this);
    }
}
