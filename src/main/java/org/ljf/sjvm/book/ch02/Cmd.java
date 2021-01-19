package org.ljf.sjvm.book.ch02;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/19 10:45
 * @description: java 命令行输入的解析类
 * @modified By：
 * @version: $ 1.0
 */
public class Cmd {
    private boolean helpFlag;

    private boolean versionFlag;

    private String cpOption;

    private String XjreOption;

    private String className;

    private String[] args;

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public String getCpOptions() {
        return cpOption;
    }

    public String getXjreOption() {
        return XjreOption;
    }

    public String getClassName() {
        return className;
    }

    public String[] getArgs() {
        return args;
    }

    public static Cmd parserCmd(String[] args) {
        Cmd cmd = new Cmd();
        int length = args.length;
        String arg = args[0];
        int offset = 0;
        if (length == 1) {
            if (arg.equals("-help") || arg.equals("-?")) {
                cmd.helpFlag = true;
            }
            if (arg.equals("-version")) {
                cmd.versionFlag = true;
            }
            //至少包括-cp classpath className
        } else if ((arg.equals("-cp") || arg.equals("-classpath")) && length >= 3) {
            cmd.cpOption = args[++offset];
            cmd.className = args[++offset];
            if (++offset < length) {
                cmd.args = new String[length - offset];
                System.arraycopy(args, offset, cmd.args, 0, length - offset);
            }
        } else {
            System.out.println("Not Support args: " + Arrays.toString(args));
            printUsage();
            System.exit(1);
        }
        return cmd;
    }

    public static void printUsage() {
        System.out.println("Usage: java setup.jar [-options] class [args...]\n" +
                "example1: java setup.jar -version\n" +
                "example2: java setup.jar -cp org.ljf.sjvm -name MyApp -args arg1,arg2");

    }

    @Override
    public String toString() {
        return "Cmd{" +
                "helpFlag=" + helpFlag +
                ", versionFlag=" + versionFlag +
                ", cpOption='" + cpOption + '\'' +
                ", className='" + className + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }


}
