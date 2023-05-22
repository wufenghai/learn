package com.example.learn.design.pattern.han._06行为型模式._03访问者模式.core;

public class Wait extends Action {

    @Override
    public void getManResult(Man man) {
        // TODO Auto-generated method stub
        System.out.println(" 男人给的评价是该歌手待定 ..");
    }

    @Override
    public void getWomanResult(Woman woman) {
        // TODO Auto-generated method stub
        System.out.println(" 女人给的评价是该歌手待定 ..");
    }

}
