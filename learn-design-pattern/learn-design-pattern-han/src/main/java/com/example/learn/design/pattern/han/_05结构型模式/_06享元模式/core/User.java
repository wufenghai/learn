package com.example.learn.design.pattern.han._05结构型模式._06享元模式.core;

//外部状态
public class User {

    private String name;


    public User(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
