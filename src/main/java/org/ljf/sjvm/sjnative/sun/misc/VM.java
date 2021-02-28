package org.ljf.sjvm.sjnative.sun.misc;

import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.sjnative.NativeMethod;

/**
 * @author: ljf
 * @date: 2021/2/28 13:27
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class VM {

    public static class Initialize extends NativeMethod{

        @Override
        public void execute(Frame frame) {
            Class vmClass = frame.getMethod().getClazz();
            SObject savedProps = vmClass.getRefVar("savedProps", "Ljava/util/Properties;");
            SObject key = StringPool.stringObject(vmClass.getLoader(), "foo");
            SObject value = StringPool.stringObject(vmClass.getLoader(), "foo");
            frame.getOperandStack().pushRef(savedProps);
            frame.getOperandStack().pushRef(key);
            frame.getOperandStack().pushRef(value);
            Class propsClass = vmClass.getLoader().loadClass("java/util/Properties");
            Method setPropMethod = propsClass.getInstanceMethod("setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
            MethodInvokeLogic.invokeMethod(frame,setPropMethod);
        }
    }
}
