package com.example.learn.data.structure.from.net.hash;

/**
 * @author wfh
 * @create 2023/5/7 16:13
 */
public class EmpLinkedList {
    private Emp head;

    //添加员工
    public void add(Emp emp) {
        //添加的是第一个员工
        if (head == null) {
            head = emp;
            return;
        }

        //添加的不是第一个员工
        Emp curemp = head;
        while (true) {
            if (curemp.getNext() == null) {
                break;
            }
            curemp = curemp.getNext();
        }
        curemp.setNext(emp);
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) +"条链表为空");
            return;
        }

        Emp curemp = head;
        System.out.print("第" + (no + 1) +"条链表信息为：");
        while (true) {
            System.out.printf("员工id：%d\t 员工姓名：%s\t",curemp.getId(),curemp.getName());
            if (curemp.getNext() == null) {
                break;
            }
            curemp = curemp.getNext();
        }
        System.out.println();
    }


    //通过员工编号查找员工
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为null");
            return null;
        }

        Emp curemp = head;
        while(true) {
            if (curemp.getId() == id) {
                return curemp;
            }
            if (curemp.getNext() == null) {
                curemp = null;
                break;
            }
            curemp = curemp.getNext();
        }
        return curemp;
    }

    //通过员工编号删除员工
    public boolean deleteEmp(int id) {
        if (head == null) {
            return false;
        }

        if (head.getNext() == null && head.getId() == id) {
            head = null;
            return true;
        }

        Emp curemp = head;
        while(true) {
            if (curemp.getNext().getId() == id) {
                curemp.setNext(curemp.getNext().getNext());
                return true;
            }
            if (curemp.getNext().getNext() == null) {
                break;
            }
        }
        return false;
    }
}

