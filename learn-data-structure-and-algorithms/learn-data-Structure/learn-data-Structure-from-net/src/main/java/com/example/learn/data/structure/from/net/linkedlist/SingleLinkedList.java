package com.example.learn.data.structure.from.net.linkedlist;

/**
 * 自定义单向链表
 * （如果把SingleLinkedList比喻成整个糖葫芦，那么Node就是糖葫芦上的山楂果）
 *
 * @author wfh
 * @create 2023/5/5 16:53
 */
public class SingleLinkedList<E> {
    /**
     * 链表大小
     */
    public int size;

    /**
     * 头结点 (先初始化一下)
     */
    public Node<E> head = new Node<E>();

    /**
     * 尾节点 (刚开始指向头结点)
     */
    public Node<E> rear = head;

    /**
     * 自定义节点
     * 再定义一个Node节点类，这个相当于一个容器。
     *
     * @param <E>
     */
    private class Node<E> {
        /**
         * data存储数据
         */
        E data;

        /**
         * 存储下个节点的引用指针
         */
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public Node() {
        }


        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 从尾部添加数据
     */
    public void addRear(E e) {
        // 根据添加的内容 创建一个新节点  next域为null
        Node<E> newNode = new Node<>(e);
        // 尾节点的next指向新节点
        rear.next = newNode;
        // 将新节点设置为尾节点
        rear = newNode;
        // 链表大小 +1
        size++;
    }

    /**
     * 从头部添加数据 （头插法）
     */
    public void addHead(E e) {
        // 根据添加的内容 创建一个新节点  next域为null
        Node<E> newNode = new Node<>(e);
        // 新增节点的next域指向头节点的next节点
        newNode.next = head.next;
        // 头结点的next域再指向新增节点
        head.next = newNode;
        size++;
    }

    /**
     * 指定插入到某个位置
     */
    public void insert(E e, int index) {
        if (index > size) {
            throw new IllegalArgumentException("下标超出链表大小");
        }
        Node temp = head;
        // 找到插入位置的前一个节点 因此是index-1 因为这是单向链表 不能指向前一个节点
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        // 根据添加的内容 创建一个新节点  next域为null
        Node<E> newNode = new Node<>(e);
        // 关键逻辑
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    /**
     * 删除指定下标的节点
     */
    public void delete(int index) {
        if (index > size) {
            throw new IllegalArgumentException("下标超出链表大小");
        }
        Node temp = head;
        // 找到插入位置的前一个节点 因此是index-1 因为这是单向链表 不能指向前一个节点
        for (int i = 1; i <= index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        // 如果删除的是最后一个节点
        if (index == size) {
            // 将倒数第二个节点设置为尾节点
            rear = temp;
        }
        size--;
    }

    /**
     * 修改指定下标的元素
     */
    public void update(E e, int index) {
        if (index > size) {
            throw new IllegalArgumentException("下标超出链表大小");
        }
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            // 注意这里是 index不是index-1  和上面的插入方法是有区别的
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
            throw new IllegalArgumentException("下标超出链表大小");
        }
        // 因为head不能动 因此这里我们需要添加一个临时指针
        Node temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.data != null) {
                System.out.println(temp.data);
            }

            // 这点很重要 临时节点后移 （别错写成 temp.next = temp 了）
            temp = temp.next;
        }
    }

    /**
     * 获取指定下标的节点值
     */
    public Object get(int index) {
        if (size == 0) {
            throw new IllegalArgumentException("下标超出链表大小");
        }
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * 链表反转
     */
    public void reverse() {
        SingleLinkedList tempLinkedList = new SingleLinkedList<>();
        Node curNode = this.head.next;
        while (true) {
            if (curNode == null) {
                break;
            }
            tempLinkedList.addHead(curNode.data);
            curNode = curNode.next;
        }
        this.head = tempLinkedList.head;
    }


    public static void main(String[] args) {
        SingleLinkedList<String> myList = new SingleLinkedList<>();
        myList.addRear("字节跳动");
        myList.addRear("阿里");
        myList.addRear("腾讯");
        myList.addRear("美团");
        myList.showList();
        System.out.println("链表的大小：" + myList.size);

        myList.insert("华为", 4);
        myList.showList();
        System.out.println("链表的大小：" + myList.size);

        myList.update("百度", 5);
        myList.showList();
        System.out.println("链表的大小：" + myList.size);

        myList.delete(3);
        myList.showList();
        System.out.println("链表的大小：" + myList.size);

        System.out.println(myList.get(1));

        myList.addHead("上官婉儿");
        myList.addHead("橘右京");
        myList.addHead("廉颇");
        myList.showList();
        System.out.println("链表的大小：" + myList.size);
        System.out.println();
        System.out.println("-------------------------------链表反转-------------------------");
        myList = new SingleLinkedList<>();
        myList.addRear("字节跳动");
        myList.addRear("阿里");
        myList.addRear("腾讯");
        myList.addRear("美团");
        myList.addRear("华为");
        myList.showList();
        myList.reverse();
        System.out.println("--------------反转后------------");
        myList.showList();


    }
}
