package com.example.demogenericity.limit.upper;

/**
 *  上限
 * @author wfh
 * @create 2024/1/29 10:32
 */
public class Info<T extends Number> {  // 此处泛型只能是数字类型

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
        Info<Integer> info = new Info<>();// 声明Integer的泛型对象
    }

}
