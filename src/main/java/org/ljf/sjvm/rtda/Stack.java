package org.ljf.sjvm.rtda;

/**
 * @author: ljf
 * @date: 2021/1/27 11:43
 * @description: java虚拟机栈
 * 单向链表实现
 * @modified By：
 * @version: $ 1.0
 */
public class Stack {
    private final long maxSize;
    private long size;
    private Frame peekFrame;

    public Stack(long maxSize) {
        this.maxSize = maxSize;
    }

    public Frame pop() {
        if (this.peekFrame == null) {
            throw new IllegalStateException("sjvm stack is empty!");
        }
        Frame top = this.peekFrame;
        this.peekFrame = top.lower;
        top.lower = null;
        this.size--;
        return top;
    }

    public void push(Frame frame) {
        if (this.size >= this.maxSize) {
            throw new StackOverflowError("sjvm stack maxSize: " + maxSize);
        }

        if (frame != null) {
            frame.lower = this.peekFrame;
        }
        this.peekFrame = frame;
        this.size++;
    }

    public Frame top() {
        if (this.peekFrame == null) {
            throw new IllegalStateException("sjvm stack is empty!");
        }
        return this.peekFrame;
    }

    public boolean isEmpty() {
        return this.peekFrame == null;
    }
}
