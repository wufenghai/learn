package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.order;

import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.Pizza;

//一个抽象工厂模式的抽象层(接口)
public interface AbsFactory {
    //让下面的工厂子类来 具体实现
    public Pizza createPizza(String orderType);
}
