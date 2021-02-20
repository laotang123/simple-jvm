package org.ljf.sjvm.examples.ch07;

/**
 * @author: ljf
 * @date: 2021/2/7 11:19
 * @description: 方法调用指令测试
 * @modified By：
 * @version: $ 1.0
 */
public class InvokeDemo implements Runnable {
    public static void staticMethod() {
    }

    private void instanceMethod() {
    }

    public void test() {
        InvokeDemo.staticMethod();//invoke_static
        InvokeDemo demo = new InvokeDemo();//init method; invoke_special
        demo.instanceMethod(); //invoke_special
        super.equals(null); //invoke_special
        this.run();//invoke_special
        ((Runnable) demo).run();
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new InvokeDemo().test();
    }
}
