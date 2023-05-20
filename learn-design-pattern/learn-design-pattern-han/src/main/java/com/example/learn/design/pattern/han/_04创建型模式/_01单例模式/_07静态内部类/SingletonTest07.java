package com.example.learn.design.pattern.han._04创建型模式._01单例模式._07静态内部类;

public class SingletonTest07 {

    public static void main(String[] args) {
        System.out.println("使用静态内部类完成单例模式");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println("instance.hashCode=" + instance.hashCode());
        System.out.println("instance2.hashCode=" + instance2.hashCode());

    }

}

// 静态内部类完成， 推荐使用
class Singleton {
    private static volatile Singleton instance;

    //构造器私有化
    private Singleton() {
    }

    //写一个静态内部类,该类中有一个静态属性 Singleton
    //1.Singleton装载的时候静态内部类不会被装载
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    //提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE
    //2.当类装载的时候线程是安全的，内部类也不例外
    public static synchronized Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}