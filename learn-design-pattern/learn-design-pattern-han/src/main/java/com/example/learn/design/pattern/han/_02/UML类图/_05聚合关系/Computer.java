package com.example.learn.design.pattern.han._02.UML类图._05聚合关系;

public class Computer {
    private Mouse mouse; //鼠标可以和computer分离
    private Moniter moniter;//显示器可以和Computer分离
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
    public void setMoniter(Moniter moniter) {
        this.moniter = moniter;
    }

}