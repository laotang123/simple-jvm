package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Slot;
import org.ljf.sjvm.rtda.Thread;
import org.ljf.sjvm.rtda.heap.Method;

/**
 * @author: ljf
 * @date: 2021/2/5 11:07
 * @description: 方法调用指令的共用代码
 * @modified By：
 * @version: $ 1.0
 */
public class MethodInvokeLogic {
    public static void invokeMethod(Frame invokeFrame, Method method) {
        Thread thread = invokeFrame.getThread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);

        int argSlotCount = method.getArgSlotCount();

        if (argSlotCount > 0) {
            Slot slot;
            for (int i = argSlotCount - 1; i >= 0; i--) {
                slot = invokeFrame.getOperandStack().popSlot();
                newFrame.setSlot(i, slot);
            }
        }

        //hack!?
        if (method.IsNative()) {
            if (method.getName().equals("registerNatives")) {
                thread.popFrame();
            } else {
                throw new NoSuchMethodError("native method: " + method.getClazz().getName() + method.getName() + method.getDescriptor());
            }
        }
    }
}
