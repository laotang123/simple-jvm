package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classfile.attributes.CodeAttribute;
import org.ljf.sjvm.classfile.attributes.LineNumberTableAttribute;

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
    private ExceptionTable exceptionTable;
    private LineNumberTableAttribute lineNumberTable;
    private int argSlotCount;//方法的参数数量

    public static Method[] newMethods(Class clazz, MemberInfo[] cfMethods) {
        Method[] methods = new Method[cfMethods.length];

        for (int i = 0; i < cfMethods.length; i++) {
            methods[i] = newMethod(clazz, cfMethods[i]);
        }
        return methods;
    }

    public static Method newMethod(Class clazz, MemberInfo cfMethod) {
        Method method = new Method();
        method.clazz = clazz;
        method.copyMemberInfo(cfMethod);
        method.copyAttributes(cfMethod);
//            System.out.println("classFile Method transfer to Class Method: " + method.getName());
        MethodDescriptorParser parser = new MethodDescriptorParser(); //TODO：全局属性或设置静态方法
        MethodDescriptor methodDescriptor = parser.parseMethodDescriptor(method.descriptor);
        method.calcArgSlotCount(methodDescriptor.getParameterTypes());

        if (method.isNative()) {
            method.injectCodeAttribute(methodDescriptor.getReturnType());
        }
        return method;
    }

    /**
     * 本地方法没有code属性，手动注入
     *
     * @param returnType：native方法返回类型
     */
    private void injectCodeAttribute(String returnType) {
        this.maxStack = 4; //todo
        this.maxLocals = this.argSlotCount;

        switch (returnType.charAt(0)) {
            case 'V':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb1}; //return
                break;
            case ':':
            case '[':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb0}; //aReturn
                break;
            case 'D':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xaf}; //dReturn
                break;
            case 'F':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xae}; //fReturn
                break;
            case 'J':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xad}; //lReturn
                break;
            default:
                this.code = new byte[]{(byte) 0xfe, (byte) 0xac}; //iReturn
        }
    }

    public int getArgSlotCount() {
        return argSlotCount;
    }

    private void calcArgSlotCount(String[] paramTypes) {
        for (String parameterType : paramTypes) {
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
            this.lineNumberTable = attribute.getLineNumberTableAttribute();
            this.exceptionTable = new ExceptionTable(attribute.getExceptionTable(), this.clazz.getConstantPool());
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

    public int findExceptionHandler(Class exClass, int pc) {
        ExceptionTable.ExceptionHandler handler = this.exceptionTable.findExceptionHandler(exClass, pc);
        if (handler != null) {
            return handler.handlerPc;
        }
        return -1;
    }

    public int getLineNumber(int pc) {
        if (this.isNative()) {
            return -2;
        }

        if (this.lineNumberTable == null) {
            return -1;
        }

        return this.lineNumberTable.getLineNumber(pc);
    }
}
