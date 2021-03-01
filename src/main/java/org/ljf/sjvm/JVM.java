package org.ljf.sjvm;

import org.ljf.sjvm.classpath.ClassPath;
import org.ljf.sjvm.exceptions.UnsupportedException;
import org.ljf.sjvm.instructions.InstructionFactory;
import org.ljf.sjvm.instructions.base.ByteCodeReader;
import org.ljf.sjvm.instructions.base.Instruction;
import org.ljf.sjvm.instructions.base.MethodInvokeLogic;
import org.ljf.sjvm.rtda.Frame;
import org.ljf.sjvm.rtda.Thread;
import org.ljf.sjvm.rtda.heap.*;
import org.ljf.sjvm.rtda.heap.Class;
import org.ljf.sjvm.rtda.heap.ClassLoader;
import org.ljf.sjvm.util.NativeUtil;

import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/3/1 10:15
 * @description: jvm类主体
 * @modified By：
 * @version: $ 1.0
 */
public class JVM {
    private Cmd cmd;
    private ClassLoader classLoader;
    private Thread mainThread;

    public JVM(Cmd cmd) {
        this.cmd = cmd;
        ClassPath container = ClassPath.parse(cmd.getXjreOption(), cmd.getCpOptions());
        System.out.println("classpath:" + container + " class:" + cmd.getClassName() + " args:" + Arrays.toString(cmd.getArgs()));
        //注册本地方法
        NativeUtil.registerNatives();

        if (container != null) {//JAVA 类库没有装载进来，parse会构建失败返回null
            this.classLoader = new ClassLoader(container);
            this.mainThread = new Thread();
        }
    }

    public void start() {
        this.initVM();
        this.execMain();
    }

    private void execMain() {
        String className = this.cmd.getClassName().replace('.', '/');
        Class mainClass = this.classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        if (mainMethod == null) {
            System.out.println("main method not found in class: " + this.cmd.getClassName());
            return;
        }
        SObject argsArr = this.createArgsArray();
        Frame frame = this.mainThread.newFrame(mainMethod);
        //FIXME 打印hello，world可能会出错？
        frame.getLocalVariableTable().setRef(0, argsArr);
        this.mainThread.pushFrame(frame);
        interpret();
    }

    private SObject createArgsArray() {
        Class stringClass = this.classLoader.loadClass("java/lang/String");
        String[] args = this.cmd.getArgs();
        int argsLen = args.length;
        SObjectArray argsArr = (SObjectArray) ((ClassArray) stringClass.arrayClass()).newArray(argsLen);
        for (int i = 0; i < argsLen; i++) {
            argsArr.setItem(i, StringPool.stringObject(this.classLoader, args[i]));
        }
        return argsArr;
    }

    private void initVM() {
        Class vmClass = this.classLoader.loadClass("sun/misc/VM");
        MethodInvokeLogic.initClass(this.mainThread, vmClass);
        interpret();
    }

    private void interpret() {
        loop();
    }

    private void loop() {
        ByteCodeReader reader = new ByteCodeReader();
        Thread thread = this.mainThread;
        while (!mainThread.isStackEmpty()) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPc();
            thread.setPc(pc);

            //decode
            reader.reset(frame.getMethod().getCode(), pc);
            short opCode = reader.readUint8();
            try {
                Instruction inst = InstructionFactory.newInstruction(opCode);
                inst.fetchOperands(reader);
                frame.setNextPc(reader.getPc());

                //execute
                inst.execute(frame);
            } catch (UnsupportedException e) {
                System.out.printf("%2d, localVariableTable: %s \n", pc, frame.getLocalVariableTable());
                e.printStackTrace();
                break;
            }

        }

    }
}
