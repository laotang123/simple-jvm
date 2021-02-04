package org.ljf.sjvm.examples.ch06;

/**
 * @author: ljf
 * @date: 2021/2/4 9:19
 * @description: 第六章测试对象加载
 * @modified By：
 * @version: $ 1.0
 */
public class MyObject {
    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768; //ldc
        MyObject myObj = new MyObject(); //new
        MyObject.staticVar = x;  //put_static
        x = MyObject.staticVar; // get_static
        myObj.instanceVar = x;  //put_field
        x = myObj.instanceVar;  //get_field
        Object obj = myObj;

        if (obj instanceof MyObject) { //instanceof
            myObj = (MyObject) obj; // check_cast
            System.out.println(myObj.instanceVar);
        }
    }
}
