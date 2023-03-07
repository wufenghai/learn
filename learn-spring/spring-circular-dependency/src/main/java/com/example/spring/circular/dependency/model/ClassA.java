package com.example.spring.circular.dependency.model;

/**
 * @author wfh
 * @create 2023/2/17 14:22
 */
public class ClassA {
    private ClassB classB;

    public ClassB getClassB() {
        return classB;
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    public ClassA(ClassB classB) {
        this.classB = classB;
    }
}
