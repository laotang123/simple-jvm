package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.Index16Instruction;
import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.ConstantPool;
import org.ljf.sjvm.rtda.heap.Method;
import org.ljf.sjvm.rtda.heap.MethodRef;

/**
 * @author: ljf
 * @date: 2021/2/7 7:42
 * @description: 指定静态方法的指令
 * 假定解析符号引用后得到方法M。M必须是静态方法，否则抛 出Incompatible-ClassChangeError异常。M不能是类初始化方法。
 * 类 初始化方法只能由Java虚拟机调用，不能使用invoke_static指令调 用。这一规则由class文件验证器保证，这里不做检查。
 * 如果声明M 的类还没有被初始化，则要先初始化该类。将在7.8小节讨论类的初始化。
 * 对于invoke_static指令，M就是最终要执行的方法，调用 InvokeMethod（）函数执行该方法。
 * @modified By:
 * @version: $ 1.0
 */
public class InvokeStatic extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Method method = methodRef.resolvedMethod();

        if (!method.isStatic()){
            throw new IncompatibleClassChangeError();
        }
        MethodInvokeLogic.invokeMethod(frame,method);
    }
}
