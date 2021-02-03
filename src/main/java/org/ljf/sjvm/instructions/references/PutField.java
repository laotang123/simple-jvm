package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.Object;

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

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                int intValue = stack.popInt();
                Object intRef = stack.popRef();
                if (intRef == null) {
                    throw new NullPointerException();
                }
                intRef.setInt(slotId, intValue);
                break;
            case 'J':
                long longValue = stack.popLong();
                Object longRef = stack.popRef();
                if (longRef == null) {
                    throw new NullPointerException();
                }
                longRef.setLong(slotId, longValue);
                break;
            case 'F':
                float floatValue = stack.popFloat();
                Object floatRef = stack.popRef();
                if (floatRef == null) {
                    throw new NullPointerException();
                }
                floatRef.setFloat(slotId, floatValue);
                break;
            case 'D':
                double doubleValue = stack.popDouble();
                Object doubleRef = stack.popRef();
                if (doubleRef == null) {
                    throw new NullPointerException();
                }
                doubleRef.setDouble(slotId, doubleValue);
                break;
            case 'L':
            case '[':
                Object value = stack.popRef();
                Object ref = stack.popRef();

                if (ref == null){
                    throw new NullPointerException();
                }

                ref.setRef(slotId,value);
            default:
                //todo
        }
    }
}
