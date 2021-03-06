package org.ljf.sjvm.sjnative;

import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.heap.Method;

/**
 * @author: ljf
 * @date: 2021/2/26 8:13
 * @description: 本地方法
 * @modified By:
 * @version: $ 1.0
 */
public abstract class NativeMethod extends Method {
    public abstract void execute(Frame frame);
}
