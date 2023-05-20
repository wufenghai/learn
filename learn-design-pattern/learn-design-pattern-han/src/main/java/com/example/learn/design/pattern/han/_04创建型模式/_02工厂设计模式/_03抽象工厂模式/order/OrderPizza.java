package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.order;


import com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    AbsFactory factory;

    // 构造器
    public OrderPizza(AbsFactory factory) {
        setFactory(factory);
    }

    private void setFactory(AbsFactory factory) {
        Pizza pizza = null;
        String orderType = ""; // 用户输入
        this.factory = factory;
        do {
            orderType = getType();
            // factory 可能是北京的工厂子类，也可能是伦敦的工厂子类
            pizza = factory.createPizza(orderType);
            if (pizza != null) { // 订购ok
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购失败");
                break;
            }
        } while (true);
    }

    // 写一个方法，可以获取客户希望订购的披萨种类
    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类:");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}