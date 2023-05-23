package com.example.learn.design.pattern.han._06行为型模式._05观察者模式.improve;

//观察者接口，有观察者来实现
public interface Observer {

    public void update(float temperature, float pressure, float humidity);
}
