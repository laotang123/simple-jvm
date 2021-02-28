package org.ljf.sjvm.instructions.references;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.OperandStack;
import org.ljf.sjvm.rtda.Thread;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.rtda.heap.StringPool;

/**
 * @author: ljf
 * @date: 2021/2/28 16:27
 * @description: athrow指令的操作数是一个异常对象引用，从操作数栈弹出。
 * 先从操作数栈中弹出异常对象引用，如果该引用是null，则抛 出NullPointerException异常，否则看是否可以找到并跳转到异常处 理代码
 * @modified By:
 * @version: $ 1.0
 */
public class AThrow extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        SObject ex = frame.getOperandStack().popRef();
        if (ex == null) {
            throw new NullPointerException();
        }

        Thread thread = frame.getThread();
        if (!findAndGotoExceptionHandler(thread, ex)) {
            handleUncaughtException(thread, ex);
        }
    }

    private void handleUncaughtException(Thread thread, SObject ex) {
        thread.clearStack();
        SObject msgObject = ex.getRefVar("detailMessage", "Ljava/lang/String");
        String detailMessage = StringPool.jString(msgObject);
        System.out.println(ex.getClazz().javaName() + ": " + detailMessage);

        //TODO: 参考go中reflect包对应java部分？
        Object extra = ex.getExtra();
        for (Class<?> anInterface : extra.getClass().getInterfaces()) {
            System.out.println("\tat " + anInterface);
        }
    }

    private boolean findAndGotoExceptionHandler(Thread thread, SObject ex) {
        for (; ; ) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPc() - 1;
            int handlerPc = frame.getMethod().findExceptionHandler(ex.getClazz(), pc);
            if (handlerPc > 0) {
                OperandStack stack = frame.getOperandStack();
                stack.clear();
                stack.pushRef(ex);
                frame.setNextPc(handlerPc);
                return true;
            }
            thread.popFrame();
            if (thread.isStackEmpty()) {
                break;
            }
        }
        return false;
    }
}
