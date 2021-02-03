package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;

/**
 * @author: ljf
 * @date: 2021/2/3 11:37
 * @description: put_field指令给实例变量赋值，它需要三个操作数。前两个操作 数是常量池索引和变量值，用法和put_static一样。
 * 第三个操作数是 对象引用，从操作数栈中弹出。
 * @modified By：
 * @version: $ 1.0
 */
public class PutField extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        Class currentClazz = currentMethod.getClazz();
        ConstantPool constantPool = currentClazz.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError("field " + field.getName() + " of class " + currentClazz.getName());
        }

        if (field.isFinal()) {
            if (currentClazz != field.getClazz() || !currentClazz.getName().equals("<init>")) {
                throw new IllegalAccessError();
            }
        }
    }
}
