package com.example.learn.design.pattern.han._06行为型模式._10策略模式.improve;

public class PekingDuck extends Duck {


    //假如北京鸭可以飞翔，但是飞翔技术一般
    public PekingDuck() {
        // TODO Auto-generated constructor stub
        flyBehavior = new BadFlyBehavior();

    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        System.out.println("~~北京鸭~~~");
    }



}
