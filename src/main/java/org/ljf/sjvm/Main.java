package org.ljf.sjvm;

import org.ljf.sjvm.classfile.ClassFile;
import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classfile.attributes.AttributeInfo;
import org.ljf.sjvm.classpath.ClassPath;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassLoader;
import org.ljf.sjvm.rtda.heap.Method;
import org.ljf.sjvm.util.IOUtil;
import org.ljf.sjvm.util.NativeUtil;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/19 11:12
 * @description: 参数解析主入口
 * @modified By：
 * @version: $ 1.0
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            Cmd.printUsage();
            System.exit(1);
        }
        Cmd cmd = Cmd.parserCmd(args);

        if (cmd.isVersionFlag()) {
            System.out.println("version 1.0");
        } else if (cmd.isHelpFlag() || cmd.getClassName().equals("")) {
            Cmd.printUsage();
        } else {
            startJVM(cmd);
//            new JVM(cmd).start();
        }

    }

    private static void startJVM(Cmd cmd) {
        ClassPath container = ClassPath.parse(cmd.getXjreOption(), cmd.getCpOptions());
        String className = cmd.getClassName();
        System.out.println("classpath:" + container + " class:" + cmd.getClassName() + " args:" + Arrays.toString(cmd.getArgs()));
        //注册本地方法
        NativeUtil.registerNatives();

        if (container != null) {//JAVA 类库没有装载进来，parse会构建失败返回null
            ClassLoader classLoader = new ClassLoader(container);
            Class mainClass = classLoader.loadClass(className);
            Method mainMethod = mainClass.getMainMethod();
            if (mainMethod != null) {
                Interpreter.interpret(mainMethod, cmd.getArgs());
            }
        }


    }

    /**
     * 从指定类容器中加载指定类名的 类
     *
     * @param className：类名
     * @param container：类容器
     * @return ：ClassFile结构的实例
     */
    private static ClassFile loadClass(String className, ClassPath container) throws ClassNotFoundException {
        byte[] classData = container.readClass(className);
        return ClassFile.parse(classData);
    }

    /**
     * 获取class file中的主方法
     *
     * @param classFile：class file对象
     * @return ：main method
     */
    private static MemberInfo getMainMethod(ClassFile classFile) throws NoSuchMethodException {
        MemberInfo[] methods = classFile.getMethods();
        for (MemberInfo method : methods) {
            if (method.getName().equals("main") && method.getDescriptor().equals("([Ljava/lang/String;)V")) {
                return method;
            }
        }
        throw new NoSuchMethodException("main method not found in class: " + classFile.getClassName());
    }


    private static void printClassInfo(ClassFile classFile) {
        System.out.println("version: " + classFile.getMajorVersion() + "." + classFile.getMinorVersion());
        System.out.println("constants count: " + classFile.getConstantPool().getConstantPoolCount());
        System.out.println("acc flags: " + classFile.getAccessFlags());
        System.out.println("this class: " + classFile.getClassName());
        System.out.println("super class: " + classFile.getSuperClassName());
        System.out.println("interfaces: " + Arrays.toString(classFile.getInterfaceNames()));
        MemberInfo[] fields = classFile.getFields();
        System.out.println("fields count: " + fields.length);
        for (MemberInfo field : fields) {
            System.out.println(field.getName());
        }
        MemberInfo[] methods = classFile.getMethods();
        System.out.println("methods count: " + methods.length);
        for (MemberInfo method : methods) {
            System.out.println(method.getName());
        }

        AttributeInfo[] attributes = classFile.getAttributes();
        System.out.println("attributes count: " + attributes.length);
        for (AttributeInfo attribute : attributes) {
            System.out.println(attribute.getClass().getName());
        }
    }
}
