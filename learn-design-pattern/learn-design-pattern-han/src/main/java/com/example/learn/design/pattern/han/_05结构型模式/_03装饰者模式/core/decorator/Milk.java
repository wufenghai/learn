package com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.decorator;

import com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.Drink;
import com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.decorator.Decorator;

public class Milk extends Decorator {

    public Milk(Drink obj) {
        super(obj);
        // TODO Auto-generated constructor stub
        setDes(" 牛奶 ");
        setPrice(2.0f);
    }

}
