package com.example.learn.data.structure.from.net.linkedlist;

/**
 * 自定义双向链表类
 *
 * @author wfh
 * @create 2023/5/5 19:44
 */
public class DoubleLinkedList<E> {
    public int size;
    public Node<E> head = new Node();
    public Node<E> rear = head;


    /**
     * 从尾部添加数据 （尾插法）
     *
     * @param e
     */
    public void addRear(E e) {
        // 根据添加的内容 创建一个新节点  next域为null
        Node<E> newNode = new Node<E>(e);
        // 尾节点的next域指向新节点
        rear.next = newNode;
        // 新节点的pre域执行尾节点
        newNode.pre = rear;
        // 将新添加的节点设置为尾节点
        rear = newNode;
        // 链表大小 +1
        size++;
    }

    /**
     * 从头部添加数据 （头插法）
     *
     * @param e
     */
    public void addHead(E e) {
        // 根据添加的内容 创建一个新节点  next域为null
        Node<E> newNode = new Node<E>(e);
        newNode.next = head.next;
        head.next.pre = newNode;
        head.next = newNode;
        newNode.pre = head;
        size++;
    }

    /**
     * 删除指定下标的节点
     *
     * @param index
     */
    public void delete(int index) {
        if (index > size) {
            throw new IllegalArgumentException("下标超出链表大小");
        }
        Node temp = head;
        // 找到对应下标的节点
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        if (index < size) {
            // 要删除的节点不是最后一个节点
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
        } else {
            // 要删除的节点是最后一个节点
            // 将倒数第二个节点设置为尾节点
            rear = temp.pre;
            // 然后将倒数第二个节点的next指向为null即可
            temp.pre.next = null;
        }
        size--;
    }

    /**
     * 修改指定下标的节点
     *
     * @param e
     * @param index
     */
    public void update(E e, int index) {
        if (index > size) {
            throw new IllegalArgumentException("下标超出链表大小");
        }
        Node temp = head;
        // 找到对应下标的节点
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        // 找到指定节点后 直接修改它的date即可
        temp.data = e;

    }

    /**
     * 遍历链表
     */
    public void showList() {
        if (size == 0) {
            throw new IllegalArgumentException("链表为空");
        }
        Node temp = head;
        // 因为head不能动 因此这里我们需要添加一个临时指针
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.data);
            // 这点很重要 临时节点后移 （别错写成 temp.next = temp 了）
            temp = temp.next;
        }
    }

    /**
     * 数据节点类
     *
     * @param <E>
     */
    private class Node<E> {
        /**
         * 存储数据
         */
        E data;

        /**
         * 指向下一个节点
         */
        Node<E> next;

        /**
         * 指向上一个节点
         */
        Node<E> pre;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.pre = null;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    ", pre=" + pre +
                    '}';
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Object> myDoublelinkedList = new DoubleLinkedList<>();
        myDoublelinkedList.addRear("华为");
        myDoublelinkedList.addRear("腾讯");
        myDoublelinkedList.addHead("字节跳动");
        myDoublelinkedList.addHead("百度");
//        myDoublelinkedList.delete(4);
        myDoublelinkedList.addRear("阿里");
        myDoublelinkedList.showList();

    }

}
