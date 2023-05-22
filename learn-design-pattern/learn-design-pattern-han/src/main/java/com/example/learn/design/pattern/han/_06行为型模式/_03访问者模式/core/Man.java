package com.example.learn.design.pattern.han._06行为型模式._03访问者模式.core;

public class Man extends Person {

    @Override
    public void accept(Action action) {
        // TODO Auto-generated method stub
        action.getManResult(this);
    }

}
