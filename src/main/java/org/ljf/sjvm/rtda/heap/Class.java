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
    private int instanceSlotCount;//实例变量空间大小 uint
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

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public Slots getStaticVars() {
        return staticVars;
    }

    public boolean isAccessibleTo(Class other) {
        return this.isPublic() || this.getPackageName().equals(other.getPackageName());
    }

    public void setLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public ClassLoader getLoader() {
        return loader;
    }

    public Class getSuperClass() {
        return superClass;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public Method[] getMethods() {
        return methods;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

    public void setSuperClass(Class superClass) {
        this.superClass = superClass;
    }

    public Class[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class[] interfaces) {
        this.interfaces = interfaces;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public long getStaticSlotCount() {
        return staticSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public void setStaticVars(Slots staticVars) {
        this.staticVars = staticVars;
    }

    public Object newObject() {
        return new Object(this, new Slots(this.instanceSlotCount));
    }


    // jvms8 6.5.instanceof
    // jvms8 6.5.checkcast
    // 参考java的 java.lang.Class.isAssignableFrom(java.lang.Class)
    public boolean isAssignableFrom(Class other) {
        Class s = other;
        Class t = this;

        if (s == t) {
            return true;
        }

        if (!t.isInterface()) {
            return s.isSubClassOf(t);
        } else {
            return s.isImplements(t);
        }
    }

    // self extends c
    private boolean isSubClassOf(Class other) {
        Class c = this.superClass; //所有类的父类至少有一个Object
        do {
            if (c == other) {
                return true;
            }
            c = c.superClass;
        } while (c != null);

        return false;
    }

    // self implements interface
    private boolean isImplements(Class iFace) {
        Class c = this;

        do {
            for (Class anInterface : c.interfaces) {
                if (anInterface == iFace || anInterface.isSubInterfaceOf(iFace)) {
                    return true;
                }
            }
            c = c.superClass;
        } while (c != null);
        return false;
    }

    // self extends iFace
    private boolean isSubInterfaceOf(Class iFace) {
        for (Class superInterface : this.interfaces) {
            if (superInterface == iFace || superInterface.isSubInterfaceOf(iFace)) {
                return true;
            }
        }
        return false;
    }

}
