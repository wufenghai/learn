package com.example.learn.data.structure.from.han._03链表._02双向链表.core;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        HeroNode newHeroNode = new HeroNode(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表");

        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");

        doubleLinkedList.list();
    }

    //创建一个双向链表的类
    static class DoubleLinkedList {
        //先初始化一个头结点，头结点不要动, 不存放具体的数据
        private HeroNode head = new HeroNode(0, "", "");

        //返回头结点
        public HeroNode getHead() {
            return head;
        }

        //添加节点到双向链表的最后
        public void add(HeroNode heroNode) {

            //因为head节点不能动，因此我们需要一个辅助指针 temp
            HeroNode temp = head;
            //遍历链表，找到最后
            while (true) {
                //找到链表的最后
                if (temp.next == null) {
                    break;
                }
                //如果没有找到最后,就将temp后移一个节点
                temp = temp.next;
            }
            //当退出while循环时，temp就指向链表的最后
            //形成一个双向链表
            temp.next = heroNode;
            heroNode.pre = temp;
        }

        //修改一个节点的内容，可以看到双向和单向的修改一样
        public void update(HeroNode heroNode) {
            //判断是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //找到需要修改的节点，根据no编号
            //定义一个辅助变量
            HeroNode temp = head.next;
            boolean flag = false;//表示是否找到该节点
            while (true) {
                if (temp == null) {
                    break;//已经遍历完这个链表
                }
                if (temp.no == heroNode.no) {
                    //找到
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //根据flag，判断是否找到要修改的节点
            if (flag) {
                temp.name = heroNode.name;
                temp.nickname = heroNode.nickname;
            } else {//没有找到
                System.out.printf("没有找到编号%d 的节点，不能修改\n", heroNode.no);
            }
        }

        //从双向链表中删除一个节点
        //1. 对应双向链表，我们可以直接找到对应的删除节点
        //2. 找到后自我删除即可
        public void del(int no) {

            //判断当前链表是否为空
            if (head.next == null) {//空链表
                System.out.println("链表为空，无法删除");
                return;
            }

            HeroNode temp = head.next;//辅助指针
            boolean flag = false;//表示是否找到待删除节点
            while (true) {
                if (temp == null) {//已经到了链表的最后
                    break;
                }
                if (temp.no == no) {
                    //找到的待删除节点的前一个节点temp
                    flag = true;
                    break;
                }
                temp = temp.next;//temp后移，遍历
            }
            //判断flag
            if (flag) {//找到
                //可以删除
                temp.pre.next = temp.next;
                //如果是最后一个节点会出现空指针异常
//                temp.next.pre = temp.pre;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }

            } else {//没有找到
                System.out.printf("没有找到编号%d 的节点，不能修改\n", no);
            }
        }

        //遍历双向链表的方法
        //显示链表[遍历]
        public void list() {
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //因为头结点不能动，因此需要一个辅助变量来遍历
            HeroNode temp = head.next;
            while (true) {
                //判断是否到了链表最后
                if (temp == null) {
                    break;
                }
                //输出节点的信息
                System.out.println(temp);
                //将temp后移，一定小心
                temp = temp.next;
            }
        }
    }

    //定义一个HeroNode，每个HeroNode 对象就是一个节点
    static class HeroNode {

        public int no;
        public String name;
        public String nickname;
        public HeroNode next;//指向下一个节点,默认为null
        public HeroNode pre;//指向上一个节点 ，默认为null

        //构造器
        public HeroNode(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        //为了显示方法，查询tostring方法
        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }
}
