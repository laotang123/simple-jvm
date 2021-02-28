package org.ljf.sjvm.examples.ch09;

import java.util.ArrayList;

/**
 * @author: ljf
 * @date: 2021/2/28 14:06
 * @description: 自动装箱和拆箱测试
 * @modified By:
 * @version: $ 1.0
 */
public class BoxTest {
    static {}
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.toString());
        for (int x : list) {
            System.out.println(x);
        }
    }
}
