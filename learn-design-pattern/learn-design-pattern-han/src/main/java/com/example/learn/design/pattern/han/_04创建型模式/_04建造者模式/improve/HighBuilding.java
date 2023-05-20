package com.example.learn.design.pattern.han._04创建型模式._04建造者模式.improve;

public class HighBuilding extends HouseBuilder {

    @Override
    public void buildBasic() {
        // TODO Auto-generated method stub
        System.out.println(" 高楼的打地基100米 ");
    }

    @Override
    public void buildWalls() {
        // TODO Auto-generated method stub
        System.out.println(" 高楼的砌墙20cm ");
    }

    @Override
    public void roofed() {
        // TODO Auto-generated method stub
        System.out.println(" 高楼的透明屋顶 ");
    }

}
