package com.example.learn.design.pattern.han._06行为型模式._02命令模式.core;

//创建命令接口
public interface Command {

    //执行动作(操作)
    public void execute();
    //撤销动作(操作)
    public void undo();
}
