package org.ljf.sjvm.rtda.heap;

/**
 * @author: ljf
 * @date: 2021/2/1 11:29
 * @description: 符号引用
 * @modified By：
 * @version: $ 1.0
 */
public abstract class SymRef implements Constant {
    protected ConstantPool constantPool;
    protected String className;
    protected Class clazz;

    public Class resolveClass() {
        if (this.clazz != null) {
            this.resolveClassRef();
        }
        return this.clazz;
    }

    //jvms8 5.4.3.1
    public void resolveClassRef() {
//        Class clazz = this.constantPool.getClazz();
//        clazz
    }
}
