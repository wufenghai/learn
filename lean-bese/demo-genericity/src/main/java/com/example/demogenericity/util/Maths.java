package com.example.demogenericity.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 为什么需要泛型？
 *
 * @author wfh
 * @create 2024/1/29 9:35
 */
public class Maths {

//    private static int add(int a, int b) {
//        System.out.println(a + "+" + b + "=" + (a + b));
//        return a + b;
//    }

//    private static float add(float a, float b) {
//        System.out.println(a + "+" + b + "=" + (a + b));
//        return a + b;
//    }

//    private static double add(double a, double b) {
//        System.out.println(a + "+" + b + "=" + (a + b));
//        return a + b;
//    }

    /*
        如果没有泛型，要实现不同类型的加法，每种类型都需要重载一个add方法；通过泛型，我们可以复用为一个方法：
     */
    private static <T extends Number> double add(T a, T b) {
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(add(17, 2));

        // 泛型中的类型在使用时指定，不需要强制类型转换（类型安全，编译器会检查类型）
        List list = new ArrayList();
        list.add("xxString");
        list.add(100d);

        /**
         *   我们在使用上述list中，list中的元素都是Object类型（无法约束其中的类型），
         *   所以在取出集合元素时需要人为的强制类型转化到具体的目标类型，
         *   且很容易出现java.lang.ClassCastException异常。
         */

        List<String> listString = new ArrayList<String>();
        // list中只能放String, 不能放其它类型的元素
        listString.add("xxString");
//        listString.add(100d);
    }

}
