package org.ljf.sjvm.examples.ch08;

import java.io.Serializable;

/**
 * @author: ljf
 * @date: 2021/2/23 16:30
 * @description: 冒泡排序测试数组相关指令
 * @modified By：
 * @version: $ 1.0
 */
public class BubbleSortTest {
    static {

    }
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

}
