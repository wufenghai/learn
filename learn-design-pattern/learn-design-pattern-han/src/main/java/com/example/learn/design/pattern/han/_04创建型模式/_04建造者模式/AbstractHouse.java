package com.example.learn.design.pattern.han._04创建型模式._04建造者模式;

public abstract class AbstractHouse {

    //打地基
    public abstract void buildBasic();
    //砌墙
    public abstract void buildWalls();
    //封顶
    public abstract void roofed();

    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }

}
