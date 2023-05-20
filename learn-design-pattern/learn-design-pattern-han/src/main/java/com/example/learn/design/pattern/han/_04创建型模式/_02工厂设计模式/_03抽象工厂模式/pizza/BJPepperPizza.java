package com.example.learn.design.pattern.han._04创建型模式._02工厂设计模式._03抽象工厂模式.pizza;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("北京的胡椒pizza");
        System.out.println(" 北京的胡椒pizza 准备原材料");
    }
}
