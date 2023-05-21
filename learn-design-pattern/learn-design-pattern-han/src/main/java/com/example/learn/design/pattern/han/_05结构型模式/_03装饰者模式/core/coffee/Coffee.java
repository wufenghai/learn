package com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.coffee;

import com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.Drink;

public class Coffee  extends Drink {

    @Override
    public float cost() {
        // TODO Auto-generated method stub
        return super.getPrice();
    }


}
