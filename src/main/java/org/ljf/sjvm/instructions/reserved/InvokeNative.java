package org.ljf.sjvm.instructions.reserved;

import org.ljf.sjvm.instructions.base.NoOperandInstruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.Method;
import org.ljf.sjvm.sjnative.NativeMethod;
import org.ljf.sjvm.sjnative.Registry;

/**
 * @author: ljf
 * @date: 2021/2/26 14:53
 * @description: 调用本地方法
 * @modified By：
 * @version: $ 1.0
 */
public class InvokeNative extends NoOperandInstruction {
    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        String className = method.getClazz().getName();
        String methodName = method.getName();

        String descriptor = method.getDescriptor();
        NativeMethod nativeMethod = Registry.findNativeMethod(className, methodName, descriptor);
        if (nativeMethod == null) {
            String methodInfo = className + "." + methodName + descriptor;
            throw new UnsatisfiedLinkError(methodInfo);
        }
        nativeMethod.execute(frame);
    }
}
