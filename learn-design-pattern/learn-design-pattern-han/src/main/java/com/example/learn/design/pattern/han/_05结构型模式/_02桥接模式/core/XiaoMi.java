package com.example.learn.design.pattern.han._05结构型模式._02桥接模式.core;

public class XiaoMi implements Brand {

    @Override
    public void open() {
        // TODO Auto-generated method stub
        System.out.println(" 小米手机开机 ");
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        System.out.println(" 小米手机关机 ");
    }

    @Override
    public void call() {
        // TODO Auto-generated method stub
        System.out.println(" 小米手机打电话 ");
    }

}
