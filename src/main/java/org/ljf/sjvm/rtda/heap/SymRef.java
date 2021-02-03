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

    /**
     * 类符号引用的解析过程，参考jvms8 5.4.3.1
     * 通俗地讲，如果类D通过符号引用N引用类C的话，要解析N， 先用D的类加载器加载C，然后检查D是否有权限访问C，如果没
     * 有，则抛出IllegalAccessError异常
     */
    public void resolveClassRef() {
        Class clazzD = this.constantPool.getClazz();
        Class clazzC = clazzD.getLoader().loadClass(this.className);

        if (!clazzC.isAccessibleTo(clazzD)) {
            throw new IllegalAccessError();
        }
        this.clazz = clazzC;
    }

}
