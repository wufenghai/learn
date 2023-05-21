package com.example.learn.design.pattern.han._05结构型模式._05外观模式.core;

public class Screen {

    private static Screen instance = new Screen();

    private Screen() {

    }
    public static Screen getInstance() {
        return instance;
    }


    public void up() {
        System.out.println(" Screen up ");
    }

    public void down() {
        System.out.println(" Screen down ");
    }


}
