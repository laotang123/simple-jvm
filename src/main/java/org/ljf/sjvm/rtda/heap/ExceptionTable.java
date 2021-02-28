package org.ljf.sjvm.rtda.heap;

import org.ljf.sjvm.classfile.attributes.CodeAttribute;

import java.time.chrono.MinguoDate;

/**
 * @author: ljf
 * @date: 2021/2/28 15:59
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class ExceptionTable {
    private final ExceptionHandler[] table;

    static class ExceptionHandler {
        int startPc;
        int endPc;
        int handlerPc;
        ClassRef catchType;

        ExceptionHandler(int startPc, int endPc, int handlerPc, ClassRef catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlerPc = handlerPc;
            this.catchType = catchType;
        }
    }

    public ExceptionTable(CodeAttribute.ExceptionTableEntry[] entries, ConstantPool constantPool) {
        this.table = new ExceptionHandler[entries.length];
        for (int i = 0; i < entries.length; i++) {
            CodeAttribute.ExceptionTableEntry entry = entries[i];
            this.table[i] = new ExceptionHandler(entry.getStartPc(),
                    entry.getEndPc(),
                    entry.getHandlerPc(),
                    getCatchType(entry.getCatchType(), constantPool));
        }
    }

    private ClassRef getCatchType(int index, ConstantPool constantPool) {
        if (index == 0) {
            return null;
        }
        return (ClassRef) constantPool.getConstant(index);
    }

    public ExceptionHandler findExceptionHandler(Class exClass,int pc){
        for (ExceptionHandler handler : table) {
            //jvms: the start_pc is inclusive and end_pc is exclusive
            if (pc >= handler.startPc && pc < handler.endPc){
                if (handler.catchType == null){
                    return handler;
                }
                Class catchClass = handler.catchType.resolvedClass();
                if (catchClass == exClass || catchClass.isSuperClassOf(exClass)){
                    return handler;
                }
            }
        }
        return null;
    }
}
