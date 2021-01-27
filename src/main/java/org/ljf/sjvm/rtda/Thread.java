package org.ljf.sjvm.rtda;

/**
 * @author: ljf
 * @date: 2021/1/27 11:40
 * @description: 线程私有数据区
 * @modified By：
 * @version: $ 1.0
 */
public class Thread {
    private int pc;
    private final Stack stack;

    public Thread(){
        this.stack = new Stack(1024);
    }
    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame){
        this.stack.push(frame);
    }

    public Frame popFrame(){
       return this.stack.pop();
    }

    public Frame currentFrame(){
        return this.stack.top();
    }
}
