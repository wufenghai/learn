package com.example.learn.design.pattern.han._05结构型模式._01适配器模式._03接口适配器;

public class Client {
    public static void main(String[] args) {

        AbsAdapter absAdapter = new AbsAdapter() {
            //只需要去覆盖我们 需要使用 接口方法
            @Override
            public void m1() {
                // TODO Auto-generated method stub
                System.out.println("使用了m1的方法");
            }
        };

        absAdapter.m1();
    }
}
