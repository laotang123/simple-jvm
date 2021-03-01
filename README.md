# simple-jvm

参考《自己动手写虚拟机》一书，使用java语言实现简易jvm。

实现功能包括

* 类加载

* 方法调用(静态方法，构造方法，实例方法(支持继承多态))

* 静态递归方法调用

  ```java
      public static void main(String[] args) {
          long x = fibonacci(10);
          System.out.println(x);
      }
      private static long fibonacci(long n) {
          if (n <= 1) {
              return n;
          }
  
          return fibonacci(n - 1) + fibonacci(n - 2);
      }
  ```

  输出结果

  ```
  55
  ```

* 数组类的创建和使用(一维int数组冒泡排序)

  ```java
      public static void main(String[] args) {
          int[] arr = {
                  22, 84, 77, 11, 95,  9, 78, 56,
                  36, 97, 65, 36, 10, 24 ,92, 48
          };
  
          //printArray(arr);
          bubbleSort(arr);
          //System.out.println(123456789);
          printArray(arr);
      }
  
      private static void bubbleSort(int[] arr) {
          boolean swapped = true;
          int j = 0;
          int tmp;
          while (swapped) {
              swapped = false;
              j++;
              for (int i = 0; i < arr.length - j; i++) {
                  if (arr[i] > arr[i + 1]) {
                      tmp = arr[i];
                      arr[i] = arr[i + 1];
                      arr[i + 1] = tmp;
                      swapped = true;
                  }
              }
          }
      }
  
      private static void printArray(int[] arr) {
          for (int i : arr) {
              System.out.println(i);
          }
      }
  ```

  输出结果

  ```
  9
  10
  11
  ...
  ```

* 简单字符串的打印



## 快速开始

* 环境：windows10，基于jdk8开发

* idea编辑器操作

  * 1.下载github原始代码：git clone https://github.com/laotang123/simple-jvm.git

  * 2.idea直接打开，点击build->build project

  * 3.执行样例，运行src/main/org/ljf/sjvm/Main函数，配置Program arguments:

    ```java
    -cp target/classes org.ljf.sjvm.examples.ch08.BubbleSortTest
    ```

    