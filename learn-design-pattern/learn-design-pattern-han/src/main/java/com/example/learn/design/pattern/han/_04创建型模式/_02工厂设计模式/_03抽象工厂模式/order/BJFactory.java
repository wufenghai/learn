package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.order;

import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.BJCheesePizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.BJPepperPizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.Pizza;

//这是工厂子类
public class BJFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("~使用的是抽象工厂模式~");
        // TODO Auto-generated method stub
        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }

}
