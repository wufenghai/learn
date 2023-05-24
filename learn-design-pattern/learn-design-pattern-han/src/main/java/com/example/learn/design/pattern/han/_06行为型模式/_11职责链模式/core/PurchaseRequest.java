package com.example.learn.design.pattern.han._06行为型模式._11职责链模式.core;

//请求类
public class PurchaseRequest {

    private int type = 0; //请求类型
    private float price = 0.0f; //请求金额
    private int id = 0;
    //构造器
    public PurchaseRequest(int type, float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }
    public int getType() {
        return type;
    }
    public float getPrice() {
        return price;
    }
    public int getId() {
        return id;
    }





}
