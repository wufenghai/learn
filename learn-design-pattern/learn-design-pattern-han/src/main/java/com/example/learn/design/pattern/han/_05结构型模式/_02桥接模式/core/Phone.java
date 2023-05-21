package com.example.learn.design.pattern.han._05结构型模式._02桥接模式.core;

//抽象类  桥的感觉 通过这个桥找到对应的Xiaomi或者vivo
public abstract class Phone {

    //组合品牌
    private Brand brand;

    //构造器
    public Phone(Brand brand) {
        super();
        this.brand = brand;
    }

    protected void open() {
        this.brand.open();
    }

    protected void close() {
        brand.close();
    }

    protected void call() {
        brand.call();
    }

}
