package com.example.demoreflex.constructors;


import java.lang.reflect.Constructor;

/*
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
 *
 * 1.获取构造方法：
 * 		1).批量的方法：
 * 			public Constructor[] getConstructors()：所有"公有的"构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

 * 		2).获取单个的方法，并调用：
 * 			public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 * 			public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 *
 * 			调用构造方法：
 * 			Constructor-->newInstance(Object... initargs)
 * @author wfh
 * @create 2024/1/29 11:14
 */
public class Constructors {

    public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.example.demoreflex.bean.Student");

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor constructor = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("constructor = " + constructor);
        //调用构造方法
        Object obj = constructor.newInstance();

        System.out.println("******************获取私有构造方法，并调用*******************************");
        Constructor declaredConstructor = clazz.getDeclaredConstructor(char.class);
        System.out.println(declaredConstructor);
        //调用构造方法
        declaredConstructor.setAccessible(true);
        Object object = declaredConstructor.newInstance('男');


    }


}
