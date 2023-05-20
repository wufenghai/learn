package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._01简单工厂模式.order;

//相当于一个客户端，发出订购
public class PizzaStore {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //new OrderPizza();

        //使用简单工厂模式
        //new OrderPizza(new SimpleFactory());
        //System.out.println("~~退出程序~~");

        new OrderPizza2();
    }

}
