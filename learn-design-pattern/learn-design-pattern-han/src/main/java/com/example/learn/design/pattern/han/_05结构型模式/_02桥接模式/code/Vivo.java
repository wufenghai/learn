package com.example.learn.design.pattern.han._05结构型模式._02桥接模式.code;

public class Vivo implements Brand {

    @Override
    public void open() {
        // TODO Auto-generated method stub
        System.out.println(" Vivo手机开机 ");
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        System.out.println(" Vivo手机关机 ");
    }

    @Override
    public void call() {
        // TODO Auto-generated method stub
        System.out.println(" Vivo手机打电话 ");
    }

}
