package com.example.learn.data.structure.from.han._03链表._01单链表.core;

import java.util.Stack;

public class SingleLinkListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkList singleLinkList = new SingleLinkList();
        //加入
//        singleLinkList.add(hero1);
//        singleLinkList.add(hero4);
//        singleLinkList.add(hero3);
//        singleLinkList.add(hero2);

        //加入按照编号的顺序
        singleLinkList.addByOrder(hero1);
        singleLinkList.addByOrder(hero4);
        singleLinkList.addByOrder(hero3);
        singleLinkList.addByOrder(hero2);

        //显示数据
        singleLinkList.list();
        //测试修改代码
        HeroNode heroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkList.update(heroNode);
        System.out.println("修改后的链表情况~~~~~~~~~~~~~~~~");
        //显示数据
        singleLinkList.list();
        System.out.println("删除后的链表情况~~~~~~~~~~~~~~~~");
        //删除一个节点
        singleLinkList.del(1);
        singleLinkList.del(4);
        //显示数据
        singleLinkList.list();

        //测试一下：求单链表中有效节点的个数
        System.out.println(getLength(singleLinkList.getHead()));

        //测试一下：查找单链表中的倒数第k个结点 【新浪面试题】
        HeroNode res = findLastIndexNode(singleLinkList.getHead(), 1);
        System.out.println("res = " + res);

        //测试一下：单链表的反转【腾讯面试题，有点难度】
        System.out.println("原来的链表情况~~~~~~~~~~~~~~~~");
        //显示数据
        singleLinkList.list();
        reversetList(singleLinkList.getHead());
        System.out.println("反转后的链表情况~~~~~~~~~~~~~~~~");
        //显示数据
        singleLinkList.list();

        //测试一下：从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
        System.out.println("原来的链表情况~~~~~~~~~~~~~~~~");
        //显示数据
        singleLinkList.list();

        System.out.println("逆序打印后的链表情况，没有改变链表的结构~~~~~~~~~~~~~~~~");
        //显示数据
        reversetPrint(singleLinkList.getHead());
    }

    //从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
    //使用方式2
    public static void reversetPrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，无法打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;//cur后移 这样就可以压入下一个节点
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }

    //单链表的反转【腾讯面试题，有点难度】
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针，帮助我们遍历原先的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点【cur】的下一个节点
        HeroNode reversetHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reversetHead 的最前端
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面有用
            cur.next = reversetHead.next;//将cur的下一个节点指向新的链表的最前端
            reversetHead.next = cur;//将 cur 连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向reversetHead.next，实现单链表的反转
        head.next = reversetHead.next;
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
    //思路
    //1. 编写一个方法，接收head节点，同事接收一个index
    //2. index 表示的是倒数第index个节点
    //3. 先把链表从头到尾遍历，等到链表的总的长度 getLength
    //4. 得到size后，我们从链表的第一个开始遍历（size-index）个,就可以得到
    //5. 如果找到了 返回该节点，否则 返回空
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一次遍历，得到链表的个数
        int size = getLength(head);
        //第二次遍历 size-index 位置， 就是我们倒数第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    //方法：获取到单链表的节点的个数（如果是带头结点的链表，需求不统计头结点）

    /**
     * @param head 链表的头结点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量,这里我们没有统计头结点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;//遍历

        }
        return length;
    }
}

//定义SingleLinkList 管理我们的英雄
class SingleLinkList {
    //先初始化一个头结点，头结点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号的顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next 指向新的节点
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
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    // (如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头结点不能动，因此我们仍然通过一个辅助指针来帮助找到添加的位置
        //因为我们这个是单链表，因此我们找的temp位于添加位置的前一个节点，否则无法插入
        HeroNode temp = head;
        boolean flag = false;//标识，添加的标号是否存在，默认为flag
        while (true) {
            if (temp.next == null) {//说明temp已经在链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到了，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {//不能添加，说明编号存在
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入了\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    //1.根据newHeroNode的no来进行修改
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

    //删除节点
    //思路
    //1. head 不能动， 因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no 和需要删除的节点的no进行比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//表示是否找到待删除节点
        while (true) {
            if (temp.next == null) {//已经到了链表的最后
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {//找到
            //可以删除
            temp.next = temp.next.next;
        } else {//没有找到
            System.out.printf("没有找到编号%d 的节点，不能修改\n", no);
        }
    }

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
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

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
