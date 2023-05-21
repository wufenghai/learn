package com.example.learn.design.pattern.han._05结构型模式._07代理模式._03Cglib代理;

public class TeacherDao {

    public String teach() {
        System.out.println(" 老师授课中  ， 我是cglib代理，不需要实现接口 ");
        return "hello";
    }
}
