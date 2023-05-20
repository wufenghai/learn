package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza;

public class BJCheesePizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("北京的奶酪pizza");
        System.out.println(" 北京的奶酪pizza 准备原材料");
    }

}
