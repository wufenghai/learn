package com.example.learn.design.pattern.han._04创建型模式._04建造者模式.improve;

//产品->Product
public class House {
    private String baise;
    private String wall;
    private String roofed;
    public String getBaise() {
        return baise;
    }
    public void setBaise(String baise) {
        this.baise = baise;
    }
    public String getWall() {
        return wall;
    }
    public void setWall(String wall) {
        this.wall = wall;
    }
    public String getRoofed() {
        return roofed;
    }
    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }

}
