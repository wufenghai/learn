package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.order;


import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.LDCheesePizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.LDPepperPizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.Pizza;

public class LDFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("~使用的是抽象工厂模式~");
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }

}
