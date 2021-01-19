package org.ljf.sjvm.book.ch02;

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
        System.out.println("classpath:" + cmd.getCpOptions() + " class:" + cmd.getClassName() + " args:" + Arrays.toString(cmd.getArgs()));
    }
}
