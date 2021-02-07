package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Object;

/**
 * @author: ljf
 * @date: 2021/2/7 10:46
 * @description: 接口方法的调用，操作码后面跟4个字节
 * @modified By：
 * @version: $ 1.0
 */
public class InvokeInterface implements Instruction {
    private int index;
    //count uint8
    //zero uint8

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        this.index = reader.readUint16();
        reader.readUint8();//count
        reader.readUint8();// must be 0
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool rtConstantPool = frame.getMethod().getClazz().getConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) rtConstantPool.getConstant(this.index);
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();

        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new IncompatibleClassChangeError();
        }
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new NullPointerException();
        }

        if (!ref.getClazz().isImplements(methodRef.resolvedClass())) {
            throw new IncompatibleClassChangeError();
        }

        Method methodToBoInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(), methodRef);

        if (methodToBoInvoked == null || methodToBoInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        if (!methodToBoInvoked.isPublic()) {
            throw new IllegalAccessError();
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBoInvoked);
    }
}
