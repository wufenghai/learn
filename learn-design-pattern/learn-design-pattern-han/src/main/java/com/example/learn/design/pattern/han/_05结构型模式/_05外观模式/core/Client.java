package com.example.learn.design.pattern.han._05结构型模式._05外观模式.core;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //这里直接调用。。 很麻烦
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();


        homeTheaterFacade.end();
    }

}
