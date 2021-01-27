package org.ljf.sjvm;

import org.ljf.sjvm.classfile.ClassFile;
import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classpath.ClassPathContainer;
import org.ljf.sjvm.util.IOUtil;

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
        }

    }

    private static void startJVM(Cmd cmd) {
        ClassPathContainer container = ClassPathContainer.parse(cmd.getXjreOption(), cmd.getCpOptions());
        System.out.println("classpath:" + container + " class:" + cmd.getClassName() + " args:" + Arrays.toString(cmd.getArgs()));
        String classPath = IOUtil.getClassPath(cmd.getClassName());

        try {
            if (container != null) {//JAVA 类库没有装载进来，parse会构建失败返回null
                ClassFile classFile = loadClass(classPath, container);
                printClassInfo(classFile);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 从指定类容器中加载指定类名的 类
     *
     * @param className：类名
     * @param container：类容器
     * @return ：ClassFile结构的实例
     */
    private static ClassFile loadClass(String className, ClassPathContainer container) throws ClassNotFoundException {
        byte[] classData = container.readClass(className);
        return ClassFile.parse(classData);
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

        System.out.println(""+classFile.getAttributes().length);
    }
}
