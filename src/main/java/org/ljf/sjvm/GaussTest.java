package org.ljf.sjvm;

/**
 * @author: ljf
 * @date: 2021/1/31 11:42
 * @description: 累加
 * @modified By:
 * @version: $ 1.0
 */
public class GaussTest {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
