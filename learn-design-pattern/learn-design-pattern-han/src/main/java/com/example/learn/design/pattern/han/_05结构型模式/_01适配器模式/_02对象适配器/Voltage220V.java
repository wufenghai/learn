package com.example.learn.design.pattern.han._05结构型模式._01适配器模式._02对象适配器;

//被适配的类
public class Voltage220V {
    //输出220V的电压，不变
    public int output220V() {
        int src = 220;
        System.out.println("电压=" + src + "伏");
        return src;
    }
}
