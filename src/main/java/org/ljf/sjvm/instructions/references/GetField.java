package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.SObject;

/**
 * @author: ljf
 * @date: 2021/2/3 13:21
 * @description: get_field指令获取对象的实例变量值，然后推入操作数栈，它需 要两个操作数。第一个操作数是uint16索引，用法和前面三个指令 一样。
 * 第二个操作数是对象引用，用法和put_field一样。
 * @modified By：
 * @version: $ 1.0
 */
public class GetField extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();//找运行时常量池的Class结构中找到对应的filed

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        OperandStack stack = frame.getOperandStack();
        SObject ref = stack.popRef();
        if (ref == null) {
            throw new NullPointerException();
        }

        //将slotId对应的值压入栈顶
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots fields = ref.getFields();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.pushInt(fields.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(fields.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(fields.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(fields.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(fields.getRef(slotId));
        }
    }
}
