package com.example.learn.design.pattern.han._05结构型模式._01适配器模式._01类适配器;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(" === 类适配器模式 ====");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }

}
