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
    private int argSlotCount;//方法的参数数量

    public static Method[] newMethods(Class clazz, MemberInfo[] cfMethods) {
        Method[] methods = new Method[cfMethods.length];
        Method method;
        for (int i = 0; i < cfMethods.length; i++) {
            method = new Method();
            method.clazz = clazz;
            method.copyMemberInfo(cfMethods[i]);
            method.copyAttributes(cfMethods[i]);
            System.out.println("classFile Method transfer to Class Method: " + method.getName());
            method.calcArgSlotCount();
            methods[i] = method;
        }
        return methods;
    }

    public int getArgSlotCount() {
        return argSlotCount;
    }

    private void calcArgSlotCount() {
        MethodDescriptorParser parser = new MethodDescriptorParser();
        MethodDescriptor parsedDescriptor = parser.parseMethodDescriptor(this.descriptor);

        for (String parameterType : parsedDescriptor.getParameterTypes()) {
            this.argSlotCount++;
            if (parameterType.equals("J") || parameterType.equals("D")) {
                this.argSlotCount++;
            }
        }

        if (!this.isStatic()) {
            this.argSlotCount++;//this reference
        }

    }

    private void copyAttributes(MemberInfo cfMethod) {
        CodeAttribute attribute = cfMethod.getCodeAttribute();
        if (attribute != null) {
            this.maxStack = attribute.getMaxStack();
            this.maxLocals = attribute.getMaxLocals();
            this.code = attribute.getCode();
        }
    }


    public boolean isSynchronized() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean isBridge() {
        return 0 != (this.accessFlags & AccessFlags.ACC_BRIDGE);
    }

    public boolean isVarargs() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VARARGS);
    }

    public boolean isNative() {
        return 0 != (this.accessFlags & AccessFlags.ACC_NATIVE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isStrict() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STRICT);
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
