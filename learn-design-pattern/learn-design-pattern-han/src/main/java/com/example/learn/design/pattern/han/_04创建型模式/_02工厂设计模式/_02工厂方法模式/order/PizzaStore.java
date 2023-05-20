package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._02工厂方法模式.order;

public class PizzaStore {

    public static void main(String[] args) {
        String loc = "bj";
        if (loc.equals("bj")) {
            //创建北京口味的各种Pizza
            new BJOrderPizza();
        } else {
            //创建伦敦口味的各种Pizza
            new LDOrderPizza();
        }
        // TODO Auto-generated method stub
    }

}

