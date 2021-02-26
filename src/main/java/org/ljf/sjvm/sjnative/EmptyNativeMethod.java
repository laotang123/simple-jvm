package org.ljf.sjvm.sjnative;

import org.ljf.sjvm.rtda.Frame;

/**
 * @author: ljf
 * @date: 2021/2/26 10:22
 * @description: 空本地方法，例如registerNatives
 * @modified By：
 * @version: $ 1.0
 */
public class EmptyNativeMethod extends NativeMethod {
    @Override
    public void execute(Frame frame) {
        //do nothings
    }
}
