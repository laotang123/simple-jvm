package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/22 11:05
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public final class ConstantClass extends ConstantInfo {
    private int nameIndex;

    ConstantClass(short tag, int nameIndex) {
        this.tag = tag;
        this.nameIndex = nameIndex;
    }
}
