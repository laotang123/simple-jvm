package org.ljf.sjvm.examples.ch09;

/**
 * @author: ljf
 * @date: 2021/2/28 10:36
 * @description: object本地方法测试
 * @modified By:
 * @version: $ 1.0
 */
public class ObjectTest {
    public static void main(String[] args) {
        ObjectTest obj1 = new ObjectTest();
        ObjectTest obj2 = new ObjectTest();
        System.out.println(obj1.hashCode());
//        System.out.println(obj1.toString());
//
//        System.out.println(obj1.equals(obj2));
    }
}
