package com.example.learn.data.structure.from.net.queue;

/**
 * 双端队列
 *
 * @author wfh
 * @create 2023/5/6 11:31
 */
public class Deque {

    private class Node {
        //数据
        int data;
        //下一个节点
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    //头节点
    public Node head;
    //尾节点
    public Node rear;

    /**
     * 在双端队列头那边添加节点变成新的头结点
     * 在第一个节点处添加一个节点
     *
     * @param e
     */
    public void addFirst(int e) {
        //创建对象初始化值建立新节点
        Node newNode = new Node(e);
        //判断尾节点是否为空
        if (this.rear == null) {
            //若为空就是头结点尾节点都是这个新创建的节点
            this.head = newNode;
            this.rear = newNode;
        }
        //newNode成为新的头节点
        newNode.next = this.head;
        this.head = newNode;
    }

    /**
     * 在双端队列尾那边添加节点变成新的尾节点
     * 在节点的最后添加一个节点
     *
     * @param e
     */
    public void addLast(int e) {
        //创建对象初始化值建立新节点
        Node newNode = new Node(e);
        //判断尾节点是否为空
        if (this.rear == null) {
            this.head = newNode;
            this.rear = newNode;
        }
        //node成为新的尾节点
        this.rear.next = newNode;
        this.rear = newNode;
    }

    /**
     * 从头结点那边拿出Deque的一个节点
     *
     * @return
     */
    public int offerFirst() {
        //判断头节点是否为空，如果是就输出！
        if (this.head == null) {
            return -1;
        }
        //如果不为空，把头结点指向的值拿出来
        int temp = this.head.data;
        //判断头结点尾节点是否重合，如果重合就表明双端队列为空
        if (this.head == this.rear) {
            this.head = null;
            this.rear = null;
        } else {
            //没有重合就接着找下一个节点变成新的头结点
            this.head = this.head.next;
        }
        return temp;
    }

    /**
     * 从尾结点那边拿出Deque的一个节点
     *
     * @return
     */
    public int offerLast() {
        //判断尾节点是否为空，如果就输出！
        if (this.rear == null) {
            return -1;
        }
        //如果不为空，把尾结点指向的值拿出来
        int temp = this.rear.data;
        //判断头结点尾节点是否重合，如果重合就表明双端队列为空
        if (this.head == this.rear) {
            this.head = null;
            this.rear = null;
        } else {
            this.rear = this.rear.next;
        }
        return temp;
    }

    /**
     * 获取Deque处第一个节点的值
     *
     * @return
     */
    public int peekFirst() {
        //判断头结点是否为空，是就输出！
        if (this.head == null) {
            return -1;
        }
        //返回头结点值
        return this.head.data;
    }

    /**
     * 获取Deque上最后一个节点的值
     *
     * @return
     */
    public int peekLast() {
        //判断尾结点是否为空，是就输出
        if (this.rear == null) {
            return -1;
        }
        //返回尾结点值
        return this.rear.data;
    }


    public boolean isEmpty() {
        return this.head == null;
    }

    public void display() {
        //临时遍历，不能直接移动真正的节点
        Node node = this.head;
        while (node != this.rear) {
            System.out.println(node.data);
            node = node.next;
        }
        System.out.println(node.data);
    }

    public static void main(String[] args) {
        Deque deque = new Deque();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.display();
        deque.offerFirst();
        System.out.println();
        deque.display();
    }

}
