package org.ljf.sjvm;

import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classfile.attributes.CodeAttribute;
import org.ljf.sjvm.exceptions.UnsupportedException;
import org.ljf.sjvm.instructions.InstructionFactory;
import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Thread;
import org.ljf.sjvm.rtda.heap.Method;

/**
 * @author: ljf
 * @date: 2021/1/30 19:06
 * @description: 简单解释器；目前只能执行一个java 方法
 * 创建一个类型为CodeAttribute的MemberInfo对象，将其作为参数调用interpret方法
 * @modified By:
 * @version: $ 1.0
 */
public class Interpreter {

    /**
     * 获取memberInfo中CodeAttribute
     * 进一步执行获得执行方法所需的局部变量表和操作数栈空间
     *
     * @param methodInfo：MethodInfo
     */
    public static void interpret(MemberInfo methodInfo) {
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        assert codeAttribute != null;
        int maxLocals = codeAttribute.getMaxLocals();
        int maxStack = codeAttribute.getMaxStack();
        byte[] byteCode = codeAttribute.getCode();
        Thread thread = new Thread();
        Frame frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);

        loop(thread, byteCode);

    }

    public static void interpret(Method method) {
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        loop(thread, method.getCode());
    }

    /**
     * loop的计算逻辑：
     * 循环执行
     * 1.计算pc
     * 2.解码指令
     * 3.执行指令
     * 直到遇见错误
     *
     * @param thread：当前线程
     * @param byteCode：方法字节数组
     */
    private static void loop(Thread thread, byte[] byteCode) {
        Frame frame = thread.popFrame();
        ByteCodeReader reader = new ByteCodeReader(byteCode);

        while (true) {
            int pc = frame.getNextPc();
            thread.setPc(pc);
            //指令解码
            reader.reset(pc);
            short opCode = reader.readUint8();
            Instruction inst;
            try {
                inst = InstructionFactory.newInstruction(opCode);
                inst.fetchOperands(reader);
                frame.setNextPc(reader.getPc());
                //指令执行
                System.out.printf("pc: %2d inst: %s \n", pc, inst);
                inst.execute(frame);
                System.out.printf("%2d, operandStack: %s, localVariableTable: %s \n", pc, frame.getOperandStack(), frame.getLocalVariableTable());
            } catch (UnsupportedException e) {
                System.out.printf("%2d, localVariableTable: %s \n", pc, frame.getLocalVariableTable());
                e.printStackTrace();
                break;
            }

        }

    }
}
