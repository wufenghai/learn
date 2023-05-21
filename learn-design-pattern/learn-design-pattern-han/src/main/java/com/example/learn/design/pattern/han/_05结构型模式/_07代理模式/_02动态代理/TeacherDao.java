package com.example.learn.design.pattern.han._05结构型模式._07代理模式._02动态代理;

public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        // TODO Auto-generated method stub
        System.out.println(" 老师授课中.... ");
    }

    @Override
    public void sayHello(String name) {
        // TODO Auto-generated method stub
        System.out.println("hello " + name);
    }

}
