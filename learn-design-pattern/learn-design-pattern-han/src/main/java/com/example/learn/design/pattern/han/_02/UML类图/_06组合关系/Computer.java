package com.example.learn.design.pattern.han._02.UML类图._06组合关系;

public class Computer {
    private Mouse mouse = new Mouse(); //鼠标可以和computer不能分离
    private Moniter moniter = new Moniter();//显示器可以和Computer不能分离
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
    public void setMoniter(Moniter moniter) {
        this.moniter = moniter;
    }

}
