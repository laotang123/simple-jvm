package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassRef;
import org.ljf.sjvm.rtda.heap.ConstantPool;
import org.ljf.sjvm.rtda.heap.Object;

/**
 * @author: ljf
 * @date: 2021/2/3 10:48
 * @description:
 * new指令的操作数是一个uint16索引，来自字节码。通过这个索引，可以从当前类的运行时常量池中找到一个类符号引用。
 * 解析这个类符号引用，拿到类数据，然后创建对象，并把对象引用推入栈 顶，new指令的工作就完成了
 *
 * 先判 断类的初始化是否已经开始，如果还没有，则需要调用类的初始化 方法，并终止指令执行。
 * 但是由于此时指令已经执行到了一半，也 就是说当前帧的nextPC字段已经指向下一条指令，所以需要修改 nextPC，让它重新指向当前指令
 * @modified By：
 * @version: $ 1.0
 */
public class New extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        Class clazz = classRef.resolvedClass();

        if (!clazz.initStarted()){
            frame.revertNextPc();
            MethodInvokeLogic.initClass(frame.getThread(),clazz);
            return;
        }

        //抽象类和接口是不能初始化的
        if (clazz.isInterface() || clazz.isAbstract()){
            throw new InstantiationError(clazz.getName());
        }

        Object ref = clazz.newObject();//实例对象的引用

        //将引用推到当前frame的操作数栈
        frame.getOperandStack().pushRef(ref);

    }
}
