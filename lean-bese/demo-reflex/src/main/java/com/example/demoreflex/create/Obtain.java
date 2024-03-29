package com.example.demoreflex.create;

import com.example.demoreflex.bean.Student;

/**
 * 获取Class对象的三种方式
 * 1 Object ——> getClass();
 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 3 通过Class类的静态方法：forName（String  className）(常用)
 *
 * @author wfh
 * @create 2024/1/29 11:08
 */
public class Obtain {

    public static void main(String[] args) {
        //第一种方式获取Class对象
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        Class stu1Class = stu1.getClass();//获取Class对象
        System.out.println(stu1Class);

        //第二种方式获取Class对象
        Class stu2Class = Student.class;
        System.out.println(stu1Class == stu2Class); //判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            Class stu3Class = Class.forName("com.example.demoreflex.bean.Student"); //注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stu3Class);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
