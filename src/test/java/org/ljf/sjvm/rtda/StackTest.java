package org.ljf.sjvm.rtda;

/**
 * @author: ljf
 * @date: 2021/1/27 13:16
 * @description: 测试栈是否满足使用要求
 * @modified By：
 * @version: $ 1.0
 */
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack(1024);
        Frame f1 = new Frame(1,1);
        Frame f2 = new Frame(1,1);
        Frame f3 = new Frame(1,1);

        stack.push(f1);
        stack.push(f2);
        stack.push(f3);

        System.out.println(stack.pop() == f3);
        System.out.println(f3.lower);
        System.out.println(f2.lower == f1);
        System.out.println(stack.pop() == f2);
        System.out.println(stack.pop() == f1);

        System.out.println(stack.pop());
    }
}
