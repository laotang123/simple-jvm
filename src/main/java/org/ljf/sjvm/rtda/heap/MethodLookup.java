package org.ljf.sjvm.rtda.heap;

/**
 * @author: ljf
 * @date: 2021/2/5 10:30
 * @description: 方法查找公用的一些方法
 * @modified By：
 * @version: $ 1.0
 */
public class MethodLookup {
    static boolean equalMethod(Method method, MemberRef memberRef) {
        return method.getName().equals(memberRef.getName()) && method.getDescriptor().equals(memberRef.getDescriptor());
    }

    static Method lookupMethodInInterfaces(Class[] interfaces, MemberRef memberRef) {
        for (Class anInterface : interfaces) {
            for (Method method : anInterface.getMethods()) {
                if (equalMethod(method, memberRef)) {
                    return method;
                }
            }

            Method method = lookupMethodInInterfaces(anInterface.getInterfaces(), memberRef);
            if (method != null) {
                return method;
            }
        }
        return null;
    }

    static Method lookupMethodInClass(Class clazzC, MethodRef methodRef) {
        do {
            for (Method method : clazzC.getMethods()) {
                if (equalMethod(method, methodRef)) {
                    return method;
                }
            }
            clazzC = clazzC.getSuperClass();
        } while (clazzC != null);
        return null;
    }
}
