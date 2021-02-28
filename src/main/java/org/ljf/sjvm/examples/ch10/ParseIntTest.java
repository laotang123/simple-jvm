package org.ljf.sjvm.examples.ch10;

/**
 * @author: ljf
 * @date: 2021/2/28 17:43
 * @description: 测试抛异常执行aThrow
 * @modified By:
 * @version: $ 1.0
 */
public class ParseIntTest {
    public static void main(String[] args) {
        foo(args);
    }

    private static void foo(String[] args) {
        try {
            bar(args);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void bar(String[] args) {
        if (args.length == 0) {
            throw new IndexOutOfBoundsException("no args!");
        }
        int x = Integer.parseInt(args[0]);
        System.out.println(x);
    }
}
