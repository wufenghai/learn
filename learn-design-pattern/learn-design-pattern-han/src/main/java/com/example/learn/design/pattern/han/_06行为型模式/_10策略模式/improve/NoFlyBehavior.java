package com.example.learn.design.pattern.han._06行为型模式._10策略模式.improve;

public class NoFlyBehavior implements FlyBehavior{

    @Override
    public void fly() {
        // TODO Auto-generated method stub
        System.out.println(" 不能飞翔  ");
    }

}
