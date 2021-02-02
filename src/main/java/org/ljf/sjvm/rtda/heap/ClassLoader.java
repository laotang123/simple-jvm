package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.ClassFile;
import org.ljf.sjvm.classpath.ClassPath;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2021/2/1 7:27
 * @description: 类加载器
 * 方法区是一个抽象的概念，classMap字段可以当做方法区的具体实现
 * @modified By:
 * @version: $ 1.0
 */
public class ClassLoader {
    private ClassPath classPath;//类的完全限定名
    private Map<String, Class> classMap;

    public ClassLoader(ClassPath classPath) {
        this.classPath = classPath;
        this.classMap = new HashMap<>();
    }

    public Class loadClass(String name) {
        Class clazz = this.classMap.get(name);//==成立，或者equals成立
        if (clazz != null) {
            return clazz;
        }
        return this.loadNoArrayClass(name);
    }

    private Class loadNoArrayClass(String name) {
        byte[] data = this.readClass(name);
        Class clazz = this.defineClass(data);
        link(clazz);
        return clazz;
    }

    private void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    /**
     * 给类变量分配空间并给与初始值
     *
     * @param clazz：类变量
     */
    private void prepare(Class clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    private void allocAndInitStaticVars(Class clazz) {
        long slotId = 0;
        if (clazz.getSuperClass() != null) {
            slotId = clazz.getSuperClass().getInstanceSlotCount();
        }

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
//            if (!field.isLongOrDouble())
        }
    }

    private void calcStaticFieldSlotIds(Class clazz) {
    }

    private void calcInstanceFieldSlotIds(Class clazz) {

    }

    private void verify(Class clazz) {
        //todo ，参考jvm8s的4.10节
    }

    //生成虚拟机可以使用的类数据，并放入到方法区
    private Class defineClass(byte[] data) {
        Class clazz = parseClass(data);
        clazz.setLoader(this);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        this.classMap.put(clazz.getName(), clazz);
        return clazz;
    }

    private void resolveInterfaces(Class clazz) {
        String[] interfaceNames = clazz.getInterfaceNames();
        int interfaceCount = interfaceNames.length;
        if (interfaceCount > 0) {
            Class[] interfaces = new Class[interfaceCount];
            //递归加载父接口
            for (int i = 0; i < interfaceCount; i++) {
                interfaces[i] = clazz.getLoader().loadClass(interfaceNames[i]);
            }
            clazz.setInterfaces(interfaces);
        }
    }

    //所有的对象都有唯一的父类，除了java.lang.object；递归加载父类
    private void resolveSuperClass(Class clazz) {
        if (!clazz.getName().equals("java/lang/Object")) {
            clazz.setSuperClass(clazz.getLoader().loadClass(clazz.getSuperClassName()));
        }
    }

    /**
     * 将字节数组解析为运行时方法区的Class结构
     *
     * @param data：字节数组
     * @return ：Class实例
     */
    private Class parseClass(byte[] data) {
        ClassFile classFile = ClassFile.parse(data);
        //throw new java.lang.ClassFormatError
        return new Class(classFile);
    }

    //读取class文件数据到内存中
    private byte[] readClass(String name) {
        byte[] data = new byte[]{};
        try {
            data = this.classPath.readClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
