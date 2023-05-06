package com.example.learn.data.structure.from.net.stack;

import java.util.Arrays;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

/**
 * 栈
 *
 * @author wfh
 * @create 2023/5/6 13:48
 */
public class Stack {

    public int[] data;
    public int size;

    public Stack() {
        this.data = new int[5];
        this.size = 0;
    }

    public Stack(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    /**
     * 入栈
     *
     * @param e
     */
    public void push(int e) {
        if (isFull()) {
            //如果栈满了就进行扩容
            this.data = Arrays.copyOf(data, this.data.length * 2);
        }
        this.data[this.size] = e;
        size++;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is empty");
        }
        int temp = this.data[this.size - 1];
        this.size--;
        return temp;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is empty");
        }
        return this.data[this.size - 1];
    }

    /**
     * 判断栈是否满
     *
     * @return
     */
    public boolean isFull() {
        return size == data.length;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);//入栈
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());//获取栈顶元素
        System.out.println(stack.pop());//出栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());//判断栈是否为空，如果为空返回true，否则返回false
    }
}
