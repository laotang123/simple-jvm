package org.ljf.sjvm.sjnative.java.lang;

import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Stack;
import org.ljf.sjvm.rtda.Thread;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.Method;
import org.ljf.sjvm.rtda.heap.SObject;
import org.ljf.sjvm.sjnative.NativeMethod;

/**
 * @author: ljf
 * @date: 2021/2/28 15:35
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class Throwable {
    /**
     * 记录java续集你栈帧信息
     */
    static class StackTraceElement {
        String fileName;
        String className;
        String methodName;
        int lineNumber;

        StackTraceElement(String fileName, String className, String methodName, int lineNumber) {
            this.fileName = fileName;
            this.className = className;
            this.methodName = methodName;
            this.lineNumber = lineNumber;
        }
    }

    public static class FillInStackTrace extends NativeMethod {

        @Override
        public void execute(Frame frame) {
            SObject thisRef = frame.getLocalVariableTable().getThis();
            frame.getOperandStack().pushRef(thisRef);
            StackTraceElement[] stackTraceElements = createStackTraceElement(thisRef, frame.getThread());
            thisRef.setExtra(stackTraceElements);
        }

        private StackTraceElement[] createStackTraceElement(SObject tObj, Thread thread) {
            int skip = distanceToObject(tObj.getClazz()) + 2;
            Frame[] frames = thread.getFrames();
            StackTraceElement[] stackTraceElements = new StackTraceElement[frames.length];
            for (int i = 0; i < frames.length; i++) {
                stackTraceElements[i] = createStackTraceElement(frames[i]);
            }
            return stackTraceElements;
        }

        private int distanceToObject(Class clazz) {
            int distance = 0;
            Class superClass = clazz.getSuperClass();
            while (superClass != null) {
                distance++;
                superClass = superClass.getSuperClass();
            }
            return distance;
        }

        private StackTraceElement createStackTraceElement(Frame frame) {
            Method method = frame.getMethod();
            Class clazz = method.getClazz();
            return new StackTraceElement(clazz.getSourceFile(),
                    clazz.javaName(), method.getName(), method.getLineNumber(frame.getNextPc()) - 1);
        }
    }
}
