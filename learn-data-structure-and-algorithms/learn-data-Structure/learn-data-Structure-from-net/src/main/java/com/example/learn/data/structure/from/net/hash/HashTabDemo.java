package com.example.learn.data.structure.from.net.hash;

import java.util.Scanner;

/**
 * @author wfh
 * @create 2023/5/7 16:15
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        int key = -1;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------1.添加雇员-----------");
            System.out.println("--------2.显示雇员-----------");
            System.out.println("--------3.查找雇员-----------");
            System.out.println("--------4.删除雇员-----------");
            System.out.println("--------0.退出系统-----------");
            key = scanner.nextInt();
            switch (key) {
                case 1:
                    System.out.println("请输入雇员id");
                    int id = scanner.nextInt();
                    System.out.println("请输入雇员姓名");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case 2:
                    hashTab.list();
                    break;
                case 3:
                    System.out.println("请输入要查找的员工编号");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case 4:
                    System.out.println("请输入要删除的员工编号");
                    id = scanner.nextInt();
                    hashTab.deleteEmp(id);
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}

