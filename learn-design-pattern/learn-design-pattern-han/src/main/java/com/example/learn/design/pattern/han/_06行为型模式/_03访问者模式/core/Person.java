package com.example.learn.design.pattern.han._06行为型模式._03访问者模式.core;

public abstract class Person {

    //提供一个方法，让访问者可以访问
    public abstract void accept(Action action);
}
