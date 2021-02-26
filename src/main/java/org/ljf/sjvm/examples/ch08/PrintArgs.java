package org.ljf.sjvm.examples.ch08;

/**
 * @author: ljf
 * @date: 2021/2/26 7:18
 * @description: hello world 测试
 * @modified By:
 * @version: $ 1.0
 */
public class PrintArgs {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
