package com.example.learn.design.pattern.han._05结构型模式._05外观模式.core;

public class Stereo {

    private static Stereo instance = new Stereo();

    private Stereo() {}
    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Stereo on ");
    }

    public void off() {
        System.out.println(" Screen off ");
    }

    public void up() {
        System.out.println(" Screen up.. ");
    }

    //...
}
