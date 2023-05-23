package com.example.learn.design.pattern.han._06行为型模式._05观察者模式.improve;

//接口, 让WeatherData 来实现
public interface Subject {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
