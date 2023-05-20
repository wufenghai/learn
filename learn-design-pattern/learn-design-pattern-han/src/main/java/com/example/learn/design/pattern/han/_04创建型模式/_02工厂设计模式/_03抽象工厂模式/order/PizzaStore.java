package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.order;

public class PizzaStore {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //new OrderPizza(new BJFactory());
        new OrderPizza(new LDFactory());
    }

}
