package org.ljf.sjvm.examples.ch09;

/**
 * @author: ljf
 * @date: 2021/2/28 11:09
 * @description: clone本地指令测试
 * @modified By:
 * @version: $ 1.0
 */
public class CloneTest implements Cloneable {
    private double pi = 3.14;

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CloneTest obj1 = new CloneTest();
        CloneTest obj2 = new CloneTest();
        obj1.pi = 3.1415926;
        System.out.println(obj1.pi);
        System.out.println(obj2.pi);

    }
}
