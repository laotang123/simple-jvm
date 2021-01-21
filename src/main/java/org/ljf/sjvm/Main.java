package org.ljf.sjvm;

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
                byte[] result = container.readClass(classPath);
                System.out.println(Arrays.toString(result));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
