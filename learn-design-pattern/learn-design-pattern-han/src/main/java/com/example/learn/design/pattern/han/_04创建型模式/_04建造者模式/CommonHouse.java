package com.example.learn.design.pattern.han._04创建型模式._04建造者模式;

public class CommonHouse extends AbstractHouse {

    @Override
    public void buildBasic() {
        // TODO Auto-generated method stub
        System.out.println(" 普通房子打地基 ");
    }

    @Override
    public void buildWalls() {
        // TODO Auto-generated method stub
        System.out.println(" 普通房子砌墙 ");
    }

    @Override
    public void roofed() {
        // TODO Auto-generated method stub
        System.out.println(" 普通房子封顶 ");
    }

}
