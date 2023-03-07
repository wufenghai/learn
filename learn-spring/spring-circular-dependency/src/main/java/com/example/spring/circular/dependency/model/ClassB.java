package com.example.spring.circular.dependency.model;

/**
 * @author wfh
 * @create 2023/2/17 14:22
 */
public class ClassB {
    private ClassA classA;

    public ClassA getClassA() {
        return classA;
    }

    public void setClassA(ClassA classA) {
        this.classA = classA;
    }

    public ClassB(ClassA classA) {
        this.classA = classA;
    }
}
