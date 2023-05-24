package com.example.learn.design.pattern.han._06行为型模式._10策略模式.improve;

public class WildDuck extends Duck {


    //构造器，传入FlyBehavor 的对象
    public  WildDuck() {
        // TODO Auto-generated constructor stub
        flyBehavior = new GoodFlyBehavior();
    }


    @Override
    public void display() {
        // TODO Auto-generated method stub
        System.out.println(" 这是野鸭 ");
    }

}
