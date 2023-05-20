package com.example.learn.design.pattern.han._05结构型模式._01适配器模式.springmvc;

//多种Controller实现
public interface Controller {

}

class HttpController implements Controller {
    public void doHttpHandler() {
        System.out.println("http...");
    }
}

class SimpleController implements Controller {
    public void doSimplerHandler() {
        System.out.println("simple...");
    }
}

class AnnotationController implements Controller {
    public void doAnnotationHandler() {
        System.out.println("annotation...");
    }
}
