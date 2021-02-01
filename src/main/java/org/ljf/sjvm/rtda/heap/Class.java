package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.ClassFile;

/**
 * @author: ljf
 * @date: 2021/2/1 7:20
 * @description: 方法区class文件结构
 * @modified By:
 * @version: $ 1.0
 */
public class Class {
    private int accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private ConstantPool constantPool;
    private Field[] fields;
    private Method[] methods;
    private ClassLoader loader;
    private Class superClass;
    private Class[] interfaces;
    private int instanceSlotCount;//实例变量空间大小
    private int staticSlotCount;//类变量空间大小
    private Slots staticVars;//静态变量


    public Class(ClassFile classFile) {
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        this.constantPool = new ConstantPool(this, classFile.getConstantPool());
        this.fields = Field.newFields(this, classFile.getFields());
        this.methods = Method.newMethods(this, classFile.getMethods());
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ENUM);
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        int i = this.name.lastIndexOf('/');
        if (i >= 0) {
            return this.name.substring(0, i);
        }
        return "";
    }

    public Slots getStaticVars() {
        return staticVars;
    }

    public boolean isAccessibleTo(Class other) {
        return this.isPublic() || this.getPackageName().equals(other.getPackageName());
    }

}
