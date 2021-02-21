package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.*;

/**
 * @author: ljf
 * @date: 2021/2/3 11:07
 * @description: get_static指令和put_static正好 相反，它取出类的某个静态变量值，然后推入栈顶
 * @modified By：
 * @version: $ 1.0
 */
public class GetStatic extends Index16Instruction {
    //当前方法解析别的类静态属性
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();
        Class clazz = field.getClazz();

        if (!clazz.initStarted()){
            frame.revertNextPc();
            MethodInvokeLogic.initClass(frame.getThread(),clazz);
            return;
        }


        /**
         * 如果解析后的字段是实例字段而非静态字段，则抛出 IncompatibleClassChangeError异常。如果是final字段，则实际操作的 是静态常量，
         * 只能在类初始化方法中给它赋值。否则，会抛出 IllegalAccessError异常。类初始化方法由编译器生成，名字是 "<clinit>"
         */
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError(field.getName());
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
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
            default:
                //todo
        }
    }
}
