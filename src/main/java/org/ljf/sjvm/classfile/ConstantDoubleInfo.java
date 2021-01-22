package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/22 13:25
 * @description: double 类型常量
 * @modified By：
 * @version: $ 1.0
 */
public class ConstantDoubleInfo extends ConstantInfo {
    private double value;

    public ConstantDoubleInfo(short tag, double value) {
        this.tag = tag;
        this.value = value;
    }
}
