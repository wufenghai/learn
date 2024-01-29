package com.example.demogenericity.interfaces;

/**
 * @author wfh
 * @create 2024/1/29 10:15
 */
public interface Info<T> { //在接口上定义泛型

    public T getVar(); // 定义抽象方法，抽象方法的返回值就是泛型类型
}
