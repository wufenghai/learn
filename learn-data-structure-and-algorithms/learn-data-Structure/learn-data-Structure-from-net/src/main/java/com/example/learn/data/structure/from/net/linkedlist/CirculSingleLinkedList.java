package com.example.learn.data.structure.from.net.linkedlist;

/**
 * 定义单向循环链表类
 *
 * @author wfh
 * @create 2023/5/5 20:24
 */
public class CirculSingleLinkedList<T> {
    public int size;        // 链表大小
    public Node<T> head = null;    // 头结点
    public Node<T> rear = null;    // 尾节点


    public void add(String data) {
        // 根据添加的内容 创建一个新节点  next域为null
        Node<T> newNode = new Node(data);
        // 插入第一个节点
        if (size == 0) {
            head = newNode;
            rear = newNode;
            // 也就是自己指向自己
            rear.next = head;
            size++;
            return;
        }
        // 尾节点的next域指向新节点
        rear.next = newNode;
        // 新增节点设置成尾节点
        rear = newNode;
        // 将尾节点的next域指向头节点
        rear.next = head;
        // 链表大小 +1
        size++;
    }

    /**
     * 从循环链表中去除某个节点
     *
     * @param name
     */
    public void remove(String name) {
        Node temp = head;
        while (true) {
            // 找到了那个节点 就是temp.next
            if (name.equals(temp.next.data)) {
                // 如果移除的节点是头结点
                if (temp.next == head) {
                    // 将头结点的下个节点设置成新的头结点
                    head = temp.next.next;
                }
                // 如果移除的节点是尾结点
                if (temp.next == rear) {
                    // 将尾结点的上个节点设置成新的尾结点
                    rear = temp;
                }
                // 移除节点
                temp.next = temp.next.next;
                System.out.println("移除节点：" + name);
                size--;
                break;
            }
            // 节点后移
            temp = temp.next;
            // 如果temp节点又变为head说明 走了一个循环还没找到要删除的节点 说明不存在
            if (temp == head) {
                System.out.println("不存在该节点");
                return;
            }
        }
    }

    public void showList() {
        if (this.size == 0) {
            System.out.println("链表为空！");
            return;
        }
        Node temp = head;
        System.out.println(temp.data + " ->");
        while (true) {
            temp = temp.next;
            if (temp == head) {
                break;
            }
            System.out.println(temp.data + " -> ");
        }
    }


    /**
     * 数据节点类
     *
     * @param <T>
     */
    private class Node<T> {
        T data;         // data存储数据
        Node<T> next;   // 存储下个节点的引用指针

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        CirculSingleLinkedList list = new CirculSingleLinkedList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.remove("1");
        list.remove("7");
        list.remove("4");
        list.showList();

    }
}
