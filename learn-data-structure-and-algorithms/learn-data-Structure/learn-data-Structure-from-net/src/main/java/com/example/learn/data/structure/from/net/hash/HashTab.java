package com.example.learn.data.structure.from.net.hash;

/**
 * @author wfh
 * @create 2023/5/7 16:14
 */
public class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0;i < size;i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        int emplinkedListArrayNo = hashFun(emp.getId());
        empLinkedListArray[emplinkedListArrayNo].add(emp);
    }

    //遍历所有的链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //查找
    public void findEmpById(int id) {
        int emplinkedListArrayNo = hashFun(id);
        Emp emp = empLinkedListArray[emplinkedListArrayNo].findEmpById(id);
        if (emp == null) {
            System.out.println("没有找到~~");
        }else {
            System.out.printf("找到了，在第%d条链表中，id = %d",emplinkedListArrayNo + 1,id);
        }
        System.out.println();
    }

    //删除员工
    public void deleteEmp(int id) {
        int emplinkedListArrayNo = hashFun(id);
        boolean res = empLinkedListArray[emplinkedListArrayNo].deleteEmp(id);
        if (res) {
            System.out.println("删除成功");
        }else {
            System.out.println("没有此员工");
        }
    }

    //散列函数 取模法
    public int hashFun(int id) {
        return id % size;
    }
}

