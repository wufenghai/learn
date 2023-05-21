package com.example.learn.design.pattern.han._05结构型模式._05外观模式.core;

public class TheaterLight {

    private static TheaterLight instance = new TheaterLight();

    private TheaterLight() {

    }
    public static TheaterLight getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" TheaterLight on ");
    }

    public void off() {
        System.out.println(" TheaterLight off ");
    }

    public void dim() {
        System.out.println(" TheaterLight dim.. ");
    }

    public void bright() {
        System.out.println(" TheaterLight bright.. ");
    }
}
