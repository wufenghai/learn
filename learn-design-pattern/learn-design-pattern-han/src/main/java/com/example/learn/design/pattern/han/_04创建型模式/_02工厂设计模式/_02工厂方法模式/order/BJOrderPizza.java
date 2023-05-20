package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._02工厂方法模式.order;

import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._02工厂方法模式.pizza.BJCheesePizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._02工厂方法模式.pizza.BJPepperPizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._02工厂方法模式.pizza.Pizza;

public class BJOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        }
        // TODO Auto-generated method stub
        return pizza;
    }

}

