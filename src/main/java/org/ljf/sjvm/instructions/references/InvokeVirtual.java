package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.SObject;

/**
 * @author: ljf
 * @date: 2021/2/4 7:33
 * @description: 实例方法返回时调用
 * @modified By:
 * @version: $ 1.0
 */
public class InvokeVirtual extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Class currentClass = frame.getMethod().getClazz();
        ConstantPool constantPool = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Method resolvedMethod = methodRef.resolvedMethod();


        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        //this reference
        SObject ref = frame.getRefFromTop(resolvedMethod.getArgSlotCount() - 1);

        if (ref == null) {
            //hack!
            if (methodRef.getName().equals("println")) {
                _println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }

            throw new NullPointerException();
        }

        if (resolvedMethod.isProtected()
                && resolvedMethod.getClazz().isSuperClassOf(currentClass)
                && !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName())
                && ref.getClazz() != currentClass
                && !ref.getClazz().isSubClassOf(currentClass)) {

            throw new IllegalAccessError();
        }

        Method methodToBoInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(), methodRef);
        if (methodToBoInvoked == null || methodToBoInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        MethodInvokeLogic.invokeMethod(frame, methodToBoInvoked);

    }

    private void _println(OperandStack stack, String descriptor) {
        switch (descriptor) {
            case "(Z)V":
                System.out.println(stack.popInt() != 0);
                break;
            case "(C)V":
                System.out.printf("%c \n", stack.popInt());
                break;
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.out.println(stack.popInt());//golang中的%v什么含义
                break;
            case "(F)V":
                System.out.println(stack.popFloat());
                break;
            case "(J)V":
                System.out.println(stack.popLong());
                break;
            case "(D)V":
                System.out.println(stack.popDouble());
                break;
            default:
                System.out.println("println: " + descriptor);
        }
    }
}
