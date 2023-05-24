package com.example.learn.design.pattern.han._06行为型模式._10策略模式.core;

public abstract class Duck {

    public Duck() {

    }

    public abstract void display();//显示鸭子信息

    public void quack() {
        System.out.println("鸭子嘎嘎叫~~");
    }

    public void swim() {
        System.out.println("鸭子会游泳~~");
    }

    public void fly() {
        System.out.println("鸭子会飞翔~~~");
    }

}
