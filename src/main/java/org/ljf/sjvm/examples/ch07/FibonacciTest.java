package org.ljf.sjvm.examples.ch07;

/**
 * @author: ljf
 * @date: 2021/2/8 6:54
 * @description: 斐波那契数列计算
 * @modified By:
 * @version: $ 1.0
 */
public class FibonacciTest {
    public static void main(String[] args) {
        long x = fibonacci(30);
        System.out.println(x);
    }
    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
