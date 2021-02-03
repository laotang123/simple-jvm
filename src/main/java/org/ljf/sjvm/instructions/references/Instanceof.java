package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassRef;
import org.ljf.sjvm.rtda.heap.ConstantPool;
import org.ljf.sjvm.rtda.heap.Object;

/**
 * @author: ljf
 * @date: 2021/2/3 13:34
 * @description: instanceof指令判断对象是否是某个类的实例（或者对象的类是 否实现了某个接口），并把结果推入操作数栈 （0 or 1）
 * @modified By：
 * @version: $ 1.0
 */
public class Instanceof extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        //instanceof指令需要两个操作数。第一个操作数是uint16索引， 从方法的字节码中获取，
        // 通过这个索引可以从当前类的运行时常量 池中找到一个类符号引用。
        // 第二个操作数是对象引用，从操作数栈中弹出
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();

        if (ref == null) {
            stack.pushInt(0);
            return;
        }

        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        Class clazz = classRef.resolvedClass();//解析引用对应的class对象
        if (ref.isInstanceof(clazz)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }
    }
}
