package com.example.demogenericity.limit.lower;

/**
 * 下限
 *
 * @author wfh
 * @create 2024/1/29 10:34
 */
public class Info<T> {

    private T var;  // 定义泛型变量

    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return this.var;
    }

    public String toString() {    // 直接打印
        return this.var.toString();
    }

    public static void main(String[] args) {
        Info<String> i1 = new Info<String>();
        Info<Object> i2 = new Info<Object>();
        i1.setVar("String");
        i2.setVar(new Object());
        fun(i1);
        fun(i2);

    }

    // 只能接手String或object类型的泛型，String类的父类只有Object
    // <? super String>：包括String和它的父类
    // <? extends String>：因为String是final，无法被继承，所以只包括null
    public static void fun(Info<? super String> temp) {
        System.out.println(temp + ", ");
    }
}
