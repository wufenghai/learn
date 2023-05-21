package com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.decorator;

import com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.Drink;
import com.example.learn.design.pattern.han._05结构型模式._03装饰者模式.core.decorator.Decorator;

//具体的Decorator， 这里就是调味品
public class Chocolate extends Decorator {

    public Chocolate(Drink obj) {
        super(obj);
        setDes(" 巧克力 ");
        setPrice(3.0f); // 调味品 的价格
    }

}
