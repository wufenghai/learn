package com.example.learn.design.pattern.han._05结构型模式._05外观模式.core;

public class Projector {

    private static Projector instance = new Projector();

    private Projector(){}
    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Projector on ");
    }

    public void off() {
        System.out.println(" Projector ff ");
    }

    public void focus() {
        System.out.println(" Projector is Projector  ");
    }

    //...
}
