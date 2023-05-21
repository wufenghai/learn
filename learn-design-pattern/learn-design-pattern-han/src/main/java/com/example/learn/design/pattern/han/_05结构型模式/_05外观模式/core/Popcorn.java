package com.example.learn.design.pattern.han._05结构型模式._05外观模式.core;

public class Popcorn {

    private static Popcorn instance = new Popcorn();

    private Popcorn() {

    }
    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" popcorn on ");
    }

    public void off() {
        System.out.println(" popcorn ff ");
    }

    public void pop() {
        System.out.println(" popcorn is poping  ");
    }
}
