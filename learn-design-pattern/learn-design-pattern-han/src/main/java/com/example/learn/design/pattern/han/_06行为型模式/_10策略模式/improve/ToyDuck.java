package com.example.learn.design.pattern.han._06行为型模式._10策略模式.improve;

public class ToyDuck extends Duck{


    public ToyDuck() {
        // TODO Auto-generated constructor stub
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        System.out.println("玩具鸭");
    }

    //需要重写父类的所有方法

    public void quack() {
        System.out.println("玩具鸭不能叫~~");
    }

    public void swim() {
        System.out.println("玩具鸭不会游泳~~");
    }


}
