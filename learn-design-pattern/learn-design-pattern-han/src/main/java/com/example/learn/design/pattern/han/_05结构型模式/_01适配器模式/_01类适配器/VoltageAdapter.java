package com.example.learn.design.pattern.han._05结构型模式._01适配器模式._01类适配器;

//适配器类
public class VoltageAdapter extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        // TODO Auto-generated method stub
        //获取到220V电压
        int srcV = output220V();
        int dstV = srcV / 44 ; //转成 5v
        return dstV;
    }

}
