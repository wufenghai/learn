package com.example.learn.design.pattern.han._01.七大设计原理._02接口隔离原则;


public class Segregation1 {

    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend1(new D());
    }

}

//接口
interface Interface1 {
    void operation1();

    void operation2();

    void operation3();

    void operation4();

    void operation5();
}

class B implements Interface1 {

    @Override
    public void operation1() {
        System.out.println("B 实现了 operation1 ");
    }

    @Override
    public void operation2() {
        System.out.println("B 实现了 operation2 ");
    }

    @Override
    public void operation3() {
        System.out.println("B 实现了 operation3 ");
    }

    @Override
    public void operation4() {
        System.out.println("B 实现了 operation4 ");
    }

    @Override
    public void operation5() {
        System.out.println("B 实现了 operation5 ");
    }
}

class D implements Interface1 {

    @Override
    public void operation1() {
        System.out.println("D 实现了 operation1 ");
    }

    @Override
    public void operation2() {
        System.out.println("D 实现了 operation2 ");
    }

    @Override
    public void operation3() {
        System.out.println("D 实现了 operation3 ");
    }

    @Override
    public void operation4() {
        System.out.println("D 实现了 operation4 ");
    }

    @Override
    public void operation5() {
        System.out.println("D 实现了 operation5 ");
    }
}


class A { //A类通过接口Interface1 依赖（使用） B类，但是只会用到1,2,3
    public void depend1(Interface1 interface1) {
        interface1.operation1();
    }

    public void depend2(Interface1 interface1) {
        interface1.operation2();
    }

    public void depend3(Interface1 interface1) {
        interface1.operation3();
    }
}

class C { //C类通过接口Interface1 依赖（使用） D类，但是只会用到1,4,5
    public void depend1(Interface1 interface1) {
        interface1.operation1();
    }

    public void depend4(Interface1 interface1) {
        interface1.operation4();
    }

    public void depend5(Interface1 interface1) {
        interface1.operation5();
    }
}

