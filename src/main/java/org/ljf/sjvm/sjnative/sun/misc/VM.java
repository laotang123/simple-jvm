package org.ljf.sjvm.sjnative.sun.misc;

import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassLoader;
import org.ljf.sjvm.sjnative.NativeMethod;

/**
 * @author: ljf
 * @date: 2021/2/28 13:27
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class VM {

    public static class Initialize extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            ClassLoader classLoader = frame.getMethod().getClazz().getLoader();
            Class jLSystemClass = classLoader.loadClass("java/lang/System");
            Method method = jLSystemClass.getStaticMethod("initializeSystemClass", "()V");
            MethodInvokeLogic.invokeMethod(frame, method);
        }
    }
}
