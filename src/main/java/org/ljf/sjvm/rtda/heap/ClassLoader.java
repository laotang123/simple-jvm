package org.ljf.sjvm.rtda.heap;

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

    }

    //生成虚拟机可以使用的类数据，并放入到方法区
    private Class defineClass(byte[] data) {
        return null;
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
