package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;

/**
 * @author: ljf
 * @date: 2021/2/3 11:07
 * @description: put_static指令给类的某个静态变量赋值，它需要两个操作数。 第一个操作数是uint16索引，来自字节码。
 * 通过这个索引可以从当 前类的运行时常量池中找到一个字段符号引用，解析这个符号引用 就可以知道要给类的哪个静态变量赋值。
 * 第二个操作数是要赋给静 态变量的值，从操作数栈中弹出。
 * @modified By：
 * @version: $ 1.0
 */
public class PutStatic extends Index16Instruction {
    //当前方法解析别的类静态属性
    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        Class currentClazz = currentMethod.getClazz();
        ConstantPool constantPool = currentClazz.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();
        Class clazz = field.getClazz();
        //todo: init class


        /**
         * 如果解析后的字段是实例字段而非静态字段，则抛出 IncompatibleClassChangeError异常。如果是final字段，则实际操作的 是静态常量，
         * 只能在类初始化方法中给它赋值。否则，会抛出 IllegalAccessError异常。类初始化方法由编译器生成，名字是 "<clinit>"
         */
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError(field.getName());
        }

        if (field.isFinal()) {
            if (currentClazz != clazz || !currentMethod.getName().equals("<clinit>")) {
                throw new IllegalAccessError();
            }
        }

        //根据字段类型从操作数栈中弹出相应的值，然后赋给静态变量
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = clazz.getStaticVars();
        OperandStack stack = frame.getOperandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                slots.setInt(slotId, stack.popInt());
                break;
            case 'F':
                slots.setFloat(slotId, stack.popFloat());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
                break;
            case 'L':
            case '[':
                slots.setRef(slotId, stack.popRef());
                break;
            default:
                //todo
        }
    }
}
