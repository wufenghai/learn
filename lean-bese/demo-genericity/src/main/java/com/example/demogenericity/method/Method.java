package com.example.demogenericity.method;

/**
 * @author wfh
 * @create 2024/1/29 10:20
 */
public class Method {

    /*
        <T>             声明此方法持有一个类型T，也可以理解为声明此方法为泛型方法
        T               指明该方法的返回值类型T
        Class<T>        作用是指明泛型T的具体类型
        clazz           用来创建泛型T代表的类的对象
     */
    public <T> T getObject(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        // 创建泛型对象
        T t = clazz.newInstance();
        return t;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Method m = new Method();
        Object object = m.getObject(Class.forName("com.example.demogenericity.clazz.ClassTest"));
        System.out.println(object);
    }
}
