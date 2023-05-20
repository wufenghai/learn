package com.example.learn.design.pattern.han._05结构型模式._01适配器模式._02对象适配器;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(" === ΆΤΟσΚΚΕδΖχΔ£Κ½ ====");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }

}
