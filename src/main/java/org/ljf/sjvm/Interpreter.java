package org.ljf.sjvm;

import com.sun.javafx.binding.StringFormatter;
import org.ljf.sjvm.classfile.MemberInfo;
import org.ljf.sjvm.classfile.attributes.CodeAttribute;
import org.ljf.sjvm.exceptions.UnsupportedException;
import org.ljf.sjvm.instructions.InstructionFactory;
import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Thread;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

    public static void interpret(Method method, String[] args) {
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
//        loop(thread, method.getCode());
        SObjectArray jArgs = createArgsArray(method.getClazz().getLoader(), args);
        frame.setRef(0, jArgs);
        loop(thread);
    }

    private static SObjectArray createArgsArray(ClassLoader loader, String[] args) {
        Class stringClass = loader.loadClass("java/lang/String");
        SObjectArray argsArr = (SObjectArray) ((ClassArray) stringClass.arrayClass()).newArray(args.length);
        for (int i = 0; i < args.length; i++) {
            argsArr.setItem(i, StringPool.stringObject(loader,args[i]));
        }
        return argsArr;
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
            //指令解码, 只有reader才会用到pc。字节的索引。其他如Thread，frame记录
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
                System.out.printf("operandStack: %s, localVariableTable: %s \n", frame.getOperandStack(), frame.getLocalVariableTable());
            } catch (UnsupportedException e) {
                System.out.printf("%2d, localVariableTable: %s \n", pc, frame.getLocalVariableTable());
                e.printStackTrace();
                break;
            }

        }

    }

    private static void loop(Thread thread) {
        ByteCodeReader reader = new ByteCodeReader();

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("instr-log2.txt"));
            while (true) {
                //方法和frame是一对一关系，frame中保存着方法执行的code和pc
                Frame frame = thread.currentFrame();
                int pc = frame.getNextPc();
                thread.setPc(pc);

                //指令解码, 只有reader才会用到pc。字节的索引。其他如Thread，frame记录
                reader.reset(frame.getMethod().getCode(), pc);
                short opCode = reader.readUint8();

                Instruction inst;
                try {
                    inst = InstructionFactory.newInstruction(opCode);
                    inst.fetchOperands(reader);
                    frame.setNextPc(reader.getPc());
                    //指令执行
//                    System.out.printf("pc: %2d inst: %s \n", pc, inst);
                    out.write(String.format("pc: %2d inst: %s \n", pc, inst));
                    inst.execute(frame);

                    if (thread.isStackEmpty()) {
                        break;
                    }
                    out.write(String.format("operandStack: %s, localVariableTable: %s \n", frame.getOperandStack(), frame.getLocalVariableTable()));
                    out.flush();
                } catch (UnsupportedException e) {
                    System.out.printf("%2d, localVariableTable: %s \n", pc, frame.getLocalVariableTable());
                    e.printStackTrace();
                    break;
                }

            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
