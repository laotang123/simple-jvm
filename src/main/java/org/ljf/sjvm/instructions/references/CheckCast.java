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
 * @date: 2021/2/3 13:54
 * @description: check_cast指令和instanceof指令很像，区别在于：instanceof指令 会改变操作数栈（弹出对象引用，推入判断结果）；
 * check_cast则不改 变操作数栈（如果判断失败，直接抛出ClassCastException异常）。
 * @modified By：
 * @version: $ 1.0
 */
public class CheckCast extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        stack.pushRef(ref);

        //null类型可以转换成任何类型
        if (ref == null) {
            return;
        }

        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);

        Class clazz = classRef.resolvedClass();
        if (!ref.isInstanceof(clazz)) {
            throw new ClassCastException();
        }
    }
}
