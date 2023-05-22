package com.example.learn.design.pattern.han._06行为型模式._03访问者模式.core;

public abstract class Action {

    //得到男性 的测评
    public abstract void getManResult(Man man);

    //得到女的 测评
    public abstract void getWomanResult(Woman woman);
}
