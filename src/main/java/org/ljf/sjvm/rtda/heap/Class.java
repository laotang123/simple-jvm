package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.ClassFile;

/**
 * @author: ljf
 * @date: 2021/2/1 7:20
 * @description: 方法区class文件结构
 * @modified By:
 * @version: $ 1.0
 */
public class Class implements Cloneable {
    protected int accessFlags;
    protected String name;
    protected String superClassName;
    protected String[] interfaceNames;
    protected ConstantPool constantPool;
    protected Field[] fields;
    protected Method[] methods;
    protected ClassLoader loader;
    protected Class superClass;
    protected Class[] interfaces;
    protected int instanceSlotCount;//实例变量空间大小 uint
    protected int staticSlotCount;//类变量空间大小
    protected Slots staticVars;//静态变量
    protected boolean initStarted;


    public boolean initStarted() {
        return this.initStarted;
    }

    public void startInit() {
        this.initStarted = true;
    }

    public Class(ClassFile classFile) {
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        this.constantPool = new ConstantPool(this, classFile.getConstantPool());
        this.fields = Field.newFields(this, classFile.getFields());
        this.methods = Method.newMethods(this, classFile.getMethods());
    }

    public Class() {
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
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

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public void setStaticVars(Slots staticVars) {
        this.staticVars = staticVars;
    }

    public SObject newObject() {
        return new SObject(this);
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
    public boolean isSubClassOf(Class other) {
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
    public boolean isImplements(Class iFace) {
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

    //c extends self
    public boolean isSuperClassOf(Class other) {
        return other.isSubClassOf(this);
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


    public Method getMainMethod() {
        try {
            return this.getStaticMethod("main", "([Ljava/lang/String;)V");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Method getClinitMethod() {
        try {
            return this.getStaticMethod("<clinit>", "()V");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Method getStaticMethod(String name, String descriptor) throws NoSuchMethodException {
        for (Method method : this.methods) {
            if (method.isStatic() && method.name.equals(name) && method.descriptor.equals(descriptor)) {
                return method;
            }
        }
        throw new NoSuchMethodException(name);
    }

    public Class arrayClass() {
        String arrayClassName = ClassNameHelper.getArrayClassName(this.name);
        return this.loader.loadClass(arrayClassName);
    }
}
