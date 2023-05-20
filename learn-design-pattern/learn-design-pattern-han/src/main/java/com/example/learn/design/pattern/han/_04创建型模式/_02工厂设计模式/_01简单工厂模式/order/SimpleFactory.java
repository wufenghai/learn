package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._01简单工厂模式.order;

import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._01简单工厂模式.pizza.CheesePizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._01简单工厂模式.pizza.GreekPizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._01简单工厂模式.pizza.PepperPizza;
import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._01简单工厂模式.pizza.Pizza;

//简单工厂类
public class SimpleFactory {

    //更加orderType 返回对应的Pizza 对象
    public Pizza createPizza(String orderType) {

        Pizza pizza = null;

        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName(" 希腊披萨 ");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName(" 奶酪披萨 ");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }

        return pizza;
    }

    //简单工厂模式 也叫 静态工厂模式

    public static Pizza createPizza2(String orderType) {

        Pizza pizza = null;

        System.out.println("使用简单工厂模式2");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName(" 希腊披萨 ");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName(" 奶酪披萨 ");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }

        return pizza;
    }

}
