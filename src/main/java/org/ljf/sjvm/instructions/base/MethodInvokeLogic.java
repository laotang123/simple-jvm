package org.ljf.sjvm.instructions.base;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Slot;
import org.ljf.sjvm.rtda.Thread;
import org.ljf.sjvm.rtda.heap.Class;
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

        //调用方法向执行方法传递参数
        if (argSlotCount > 0) {
            Slot slot;
            for (int i = argSlotCount - 1; i >= 0; i--) {
                slot = invokeFrame.getOperandStack().popSlot();
                newFrame.setSlot(i, slot);
            }
        }

        //hack!?
        if (method.isNative()) {
            if (method.getName().equals("registerNatives")) {
                thread.popFrame();
            } else {
                throw new NoSuchMethodError("native method: " + method.getClazz().getName() + method.getName() + method.getDescriptor());
            }
        }
    }

    public static void initClass(Thread thread, Class clazz) {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, Class clazz) {
        Method clinitMethod = clazz.getClinitMethod();
        if (clinitMethod != null) {
            //exec <clinit>
            Frame newFrame = thread.newFrame(clinitMethod);
            thread.pushFrame(newFrame);
        }
    }

    //递归初始化父类 初始化方法
    private static void initSuperClass(Thread thread, Class clazz) {
        if (!clazz.isInterface()) {
            Class superClass = clazz.getSuperClass();
            if (superClass != null && !superClass.initStarted()) {
                initClass(thread, superClass);
            }
        }
    }
}
