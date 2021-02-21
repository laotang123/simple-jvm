package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.ClassFile;

/**
 * @author: ljf
 * @date: 2021/2/21 12:52
 * @description: 类数组
 * @modified By：
 * @version: $ 1.0
 */
public class ClassArray extends Class {

    public ClassArray(ClassFile classFile) {
        super(classFile);
    }

    public ClassArray(ClassLoader loader, String name) {
        super();
        this.accessFlags = AccessFlags.ACC_PUBLIC;
        this.name = name;
        this.loader = loader;
        this.initStarted = true;
        this.superClass = loader.loadClass("java/lang/Object");
        this.interfaces = new Class[]{
                loader.loadClass("java/lang/Cloneable"),
                loader.loadClass("java/lang/Serializable")
        };
    }

    public boolean isArray() {
        return this.name.charAt(0) == '[';
    }

    public SObject newArray(int count) {
        if (!this.isArray()) {
            throw new IllegalArgumentException("not array class: " + this.name);
        }

        switch (this.name) {
            case "[Z": //布尔数组用字节数组代替
            case "[B":
                return new SObjectArray(this, new Byte[count]);
            case "[C":
                return new SObjectArray(this, new Character[count]);
            case "[S":
                return new SObjectArray(this, new Short[count]);
            case "[I":
                return new SObjectArray(this, new Integer[count]);
            case "[J":
                return new SObjectArray(this, new Long[count]);
            case "[F":
                return new SObjectArray(this, new Float[count]);
            case "[D":
                return new SObjectArray(this, new Double[count]);
            default:
                return new SObjectArray(this, new Object[count]);
        }
    }
}
