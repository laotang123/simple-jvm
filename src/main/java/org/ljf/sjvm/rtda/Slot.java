package org.ljf.sjvm.rtda;

import org.ljf.sjvm.rtda.heap.SObject;

/**
 * @author: ljf
 * @date: 2021/1/27 13:28
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class Slot {
    public int num;
    public SObject ref;


    public Slot() {
    }

    public Slot(int num) {
        this.num = num;
    }

    public Slot(SObject ref) {
        this.ref = ref;
    }

    public Slot(int num, SObject ref) {
        this.num = num;
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "num=" + num +
                ", ref=" + ref +
                '}';
    }
}
