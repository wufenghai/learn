package com.example.learn.design.pattern.han._05结构型模式._01适配器模式.springmvc;


///定义一个Adapter接口
public interface HandlerAdapter {
    public boolean supports(Object handler);

    public void handle(Object handler);
}

// 多种适配器类

class SimpleHandlerAdapter implements HandlerAdapter {

    public void handle(Object handler) {
        ((SimpleController) handler).doSimplerHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof SimpleController);
    }

}

class HttpHandlerAdapter implements HandlerAdapter {

    public void handle(Object handler) {
        ((HttpController) handler).doHttpHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof HttpController);
    }

}

class AnnotationHandlerAdapter implements HandlerAdapter {

    public void handle(Object handler) {
        ((AnnotationController) handler).doAnnotationHandler();
    }

    public boolean supports(Object handler) {

        return (handler instanceof AnnotationController);
    }

}