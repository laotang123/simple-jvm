package org.ljf.sjvm.book.ch01;

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
    @Parameter(names = {"-help", "-?"}, description = "print help message")
    private boolean helpFlag;

    @Parameter(names = "-version", description = "print version and exit")
    private boolean versionFlag;

    @Parameter(names = {"-classpath", "-cp"}, description = "classpath")
    private String cpOption;

    @Parameter(names = "-name", description = "name of class")
    private String className;

    @Parameter(names = "-args", description = "classpath")
    private String args;

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public String getCpOptions() {
        return cpOption;
    }

    public String getClassName() {
        return className;
    }

    public String getArgs() {
        return args;
    }

    public static Cmd parserCmd(String[] args) {
        Cmd cmd = new Cmd();
//        System.out.println("help 默认值：" + cmd.isHelpFlag());
        JCommander.newBuilder()
                .addObject(cmd)
                .build()
                .parse(args);
        return cmd;
    }

    public static void printUsage(String jarName) {
        System.out.println("Usage: " + jarName + " [-options] -className value -args [args...]\n" +
                "example1: " + jarName + " -version\n" +
                "example2: " + jarName + "-cp org.ljf.sjvm -name MyApp -args arg1,arg2");

    }

    @Override
    public String toString() {
        return "Cmd{" +
                "helpFlag=" + helpFlag +
                ", versionFlag=" + versionFlag +
                ", cpOption='" + cpOption + '\'' +
                ", className='" + className + '\'' +
                ", args=" + args +
                '}';
    }
}
