package com.example.learn.data.structure.from.net.queue;

/**
 * 普通队列 通过链表实现 先进先出
 * @author wfh
 * @create 2023/5/6 10:10
 */
public class Queue {
    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node head;
    public Node rear;

    /**
     * 入队
     *
     * @param data
     */
    public void offer(int data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
            this.rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public int poll() {
        if (isEmpty()) {
            throw new NullPointerException("队列为空");
        }
        int data = this.head.data;
        this.head = this.head.next;
        return data;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * 获取对头元素
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new NullPointerException("队列为空");
        }
        return this.head.data;
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
