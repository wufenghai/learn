package com.example.learn.design.pattern.han._05结构型模式._05外观模式.core;

public class DVDPlayer {

    //使用单例模式, 使用饿汉式
    private static DVDPlayer instance = new DVDPlayer();

    private DVDPlayer() {
    }
    public static DVDPlayer getInstanc() {
        return instance;
    }

    public void on() {
        System.out.println(" dvd on ");
    }
    public void off() {
        System.out.println(" dvd off ");
    }

    public void play() {
        System.out.println(" dvd is playing ");
    }

    //....
    public void pause() {
        System.out.println(" dvd pause ..");
    }
}