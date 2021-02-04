package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classfile.attributes.CodeAttribute;

/**
 * @author: ljf
 * @date: 2021/2/1 7:26
 * @description: class的方法
 * @modified By:
 * @version: $ 1.0
 */
public class Method extends ClassMember {
    private int maxStack;
    private int maxLocals;
    private byte[] code;

    public static Method[] newMethods(Class clazz, MemberInfo[] cfMethods) {
        Method[] methods = new Method[cfMethods.length];
        Method method;
        for (int i = 0; i < cfMethods.length; i++) {
            method = new Method();
            method.clazz = clazz;
            method.copyMemberInfo(cfMethods[i]);
            method.copyAttributes(cfMethods[i]);
            methods[i] = method;
        }
        return methods;
    }

    private void copyAttributes(MemberInfo cfMethod) {
        CodeAttribute attribute = cfMethod.getCodeAttribute();
        if (attribute != null) {
            this.maxStack = attribute.getMaxStack();
            this.maxLocals = attribute.getMaxLocals();
            this.code = attribute.getCode();
        }
    }


    public boolean IsSynchronized() {
        return 0 != (this.accessFlags& AccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean IsBridge() {
        return 0 != (this.accessFlags& AccessFlags.ACC_BRIDGE);
    }

    public boolean IsVarargs() {
        return 0 != (this.accessFlags& AccessFlags.ACC_VARARGS);
    }

    public boolean IsNative() {
        return 0 != (this.accessFlags& AccessFlags.ACC_NATIVE);
    }
    public boolean IsAbstract() {
        return 0 != (this.accessFlags& AccessFlags.ACC_ABSTRACT);
    }

    public boolean IsStrict() {
        return 0 != (this.accessFlags& AccessFlags.ACC_STRICT);
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }
}
