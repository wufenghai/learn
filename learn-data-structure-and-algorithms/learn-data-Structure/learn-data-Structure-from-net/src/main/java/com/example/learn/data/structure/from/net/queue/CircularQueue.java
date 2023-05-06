package com.example.learn.data.structure.from.net.queue;

/**
 * 循环队列
 *
 * @author wfh
 * @create 2023/5/6 11:16
 */
public class CircularQueue {

    public int[] data;
    public int head;
    public int rear;


    public CircularQueue(int capacity) {
        this.data = new int[capacity + 1];
    }

    /**
     * 入队
     *
     * @param e
     * @return
     */
    public boolean enQueue(int e) {
        if (isFull()) {
            return false;
        }
        this.data[rear] = e;
        rear = (rear + 1) % data.length;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % data.length;
        return true;
    }

    /**
     * 获取对头数据
     *
     * @return
     */
    public int getHead() {
        if (isEmpty()) {
            return -1;
        }
        return this.data[head];
    }

    /**
     * 获取队尾元素
     *
     * @return
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        if (rear == 0) {
            return data[data.length - 1];
        } else {
            return data[rear - 1];
        }

    }

    /**
     * 判断队列是否满了
     *
     * @return
     */
    public boolean isFull() {
        if ((rear + 1) % data.length == head) {
            return true;
        }
        return false;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return head == rear;
    }

}
