package com.example.demogenericity.interfaces;

/**
 * @author wfh
 * @create 2024/1/29 10:16
 */
public class InfoImpl<T> implements Info<T> {

    private T var;

    public InfoImpl(T var) {
        this.setVar(var);
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public T getVar() {
        return this.var;
    }

    public static void main(String[] args) {
        Info<String> info = new InfoImpl<>("123");
        System.out.println(info.getVar());
    }
}
