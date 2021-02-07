package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.Object;

/**
 * @author: ljf
 * @date: 2021/2/4 7:30
 * @description: 在创建类实例时，编译器会在new指令的后面加入invoke_special指令来调用构造函数初始化对象
 * 空的实现
 * invoke_special指令用来调 用无须动态绑定的实例方法，包括构造函数、私有方法和通过super
 * 关键字调用的超类方法。(编译时就能确定的方法)剩下的情况则属于动态绑定。
 * @modified By:
 * @version: $ 1.0
 */
public class InvokeSpecial extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Class currentClass = frame.getMethod().getClazz();
        ConstantPool rtConstantPool = currentClass.getConstantPool();//运行时常量池
        MethodRef methodRef = (MethodRef) rtConstantPool.getConstant(this.index);
        Class resolvedClass = methodRef.resolvedClass();
        Method resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod.getName().equals("<init>") && resolvedMethod.getClazz() != resolvedClass) {
            throw new NoSuchMethodError();
        }

        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        //this reference
        Object ref = frame.getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new NullPointerException();
        }

        //确保protected方法只能被声明该方法的类或子类 调用。
        if (resolvedMethod.isProtected()
                && resolvedMethod.getClazz().isSuperClassOf(currentClass)
                && !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName())
                && ref.getClazz() != currentClass
                && !ref.getClazz().isSuperClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        Method methodToBoInvoked = resolvedMethod;
        if (currentClass.isSuper()
                && resolvedClass.isSuperClassOf(currentClass)
                && !resolvedMethod.getName().equals("<init>")) {
            //去父类查找的过程
            methodToBoInvoked = MethodLookup.lookupMethodInClass(currentClass.getSuperClass(), methodRef);
        }

        if (methodToBoInvoked == null || methodToBoInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        MethodInvokeLogic.invokeMethod(frame, methodToBoInvoked);
    }
}
