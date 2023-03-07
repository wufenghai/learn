package com.example.spring.framework.bean;

/**
 * @author wfh
 * @create 2023/3/1 9:01
 */
public class HelloWorld {

    public HelloWorld() {
        System.out.println("测试得知：创建对象时确实调用了无参数构造方法。");
    }

    public void sayHello(){
        System.out.println("helloworld");
    }
}
