package com.example.demofunction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.*;

//@SpringBootTest
class DemoFunctionApplicationTests {

    @Test
    void contextLoads() {

    }

    //------------------------------Function函数------------------------------------------
    //-----------------------------Function<T, R>--------------------------------
    // ① R apply(T t)
    // 将此参数应用到函数中
    @Test
    public void test01() {
        Function<String, String> function = a -> a + " Jack!";
        System.out.println(function.apply("Hello")); // Hello Jack!
    }

    // ② Function<T, R> andThen(Function<? super R,? extends V> after)
    // 返回一个组合函数，该函数结果应用到after函数中
    @Test
    public void test02() {
        Function<String, String> function = a -> a + " Jack!";
        Function<String, String> function1 = a -> a + " Bob!";
        String greet = function.andThen(function1).apply("Hello");
        System.out.println(greet); // Hello Jack! Bob!
    }

    // ③ Function<T, R> compose(Function<? super V,? extends T> before)
    // 返回一个组合函数，首先将入参应用到before函数，再将before函数结果应用到该函数中
    @Test
    public void test03() {
        Function<String, String> function = a -> a + " Jack!";
        Function<String, String> function1 = a -> a + " Bob!";
        String greet = function.compose(function1).apply("Hello");
        System.out.println(greet); // Hello Bob! Jack!
    }


    //---------------------------------BiFunction<T, U, R>-----------------------
    // ① R apply(T t, U u)
    // 将参数应用于函数执行
    @Test
    public void test11() {
        BiFunction<String, String, String> biFunction = (a, b) -> a + b;
        System.out.println(biFunction.apply("Hello ", "Jack!")); // Hello Jack!
    }

    // ② BiFunction<T,U,V> andThen(Function<? super R,? extends V> after)
    // 返回一个组合函数，after函数应用于该函数之后
    @Test
    public void test12() {
        BiFunction<String, String, String> biFunction = (a, b) -> a + b;
        Function<String, String> function = (a) -> a + "!!!";
        System.out.println(biFunction.andThen(function).apply("Hello", " Jack")); // Hello Jack!!!
    }















    //---------------------------Consumer消费者--------------------------
    //----------------------------Consumer<T>--------------------------
    // ①void accept(T t)
    // 对给定的参数执行操作
    @Test
    public void test21() {
        StringBuilder sb = new StringBuilder("Hello ");
        Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
        consumer.accept(sb);
        System.out.println(sb.toString());    // Hello Jack!
    }

    // ② default Consumer andThen(Consumer<? super T> after)
    // 返回一个组合函数，after将会在该函数执行之后应用
    @Test
    public void test22() {
        StringBuilder sb = new StringBuilder("Hello ");
        Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
        Consumer<StringBuilder> consumer1 = (str) -> str.append(" Bob!");
        consumer.andThen(consumer1).accept(sb);
        System.out.println(sb.toString());    // Hello Jack! Bob!
    }

    //-------------------------------BiConsumer<T,U>---------------
    // ① void accept(T t, U u)
    // 对给定的参数执行操作
    @Test
    public void test31() {
        StringBuilder sb = new StringBuilder();
        BiConsumer<String, String> biConsumer = (a, b) -> {
            sb.append(a);
            sb.append(b);
        };
        biConsumer.accept("Hello ", "Jack!");
        System.out.println(sb.toString());    // Hello Jack!
    }

    // ② default BiConsumer<T,U> andThen(BiConsumer<? super T,? super U> after)
    // 返回一个组合函数，after将会在该函数执行之后应用
    @Test
    public void test32() {
        StringBuilder sb = new StringBuilder();
        BiConsumer<String, String> biConsumer = (a, b) -> {
            sb.append(a);
            sb.append(b);
        };
        BiConsumer<String, String> biConsumer1 = (a, b) -> {
            System.out.println(a + b);
        };
        biConsumer.andThen(biConsumer1).accept("Hello", " Jack!"); // Hello Jack!
    }












    //-----------------------------Predicate谓语--------------------------------
    //---------------------------------Predicate<T>--------------------------
    //① boolean test(T t)
    // 根据给定的参数进行判断
    @Test
    public void test41() {
        Predicate<Integer> predicate = number -> number != 0;
        System.out.println(predicate.test(10));    //true
    }

    // ② Predicate and(Predicate<? super T> other)
    // 返回一个组合判断，将other以短路与的方式加入到函数的判断中
    @Test
    public void test42() {
        Predicate<Integer> predicate = number -> number != 0;
        predicate = predicate.and(number -> number >= 10);
        System.out.println(predicate.test(10));    //true
    }

    // ③ Predicate or(Predicate<? super T> other)
    // 返回一个组合判断，将other以短路或的方式加入到函数的判断中
    @Test
    public void test43() {
        Predicate<Integer> predicate = number -> number != 0;
        predicate = predicate.or(number -> number != 10);
        System.out.println(predicate.test(10));    //true
    }

    // ④ Predicate negate()
    // 将函数的判断取反
    @Test
    public void test44() {
        Predicate<Integer> predicate = number -> number != 0;
        predicate = predicate.negate();
        System.out.println(predicate.test(10));    //false
    }

    //-----------------------------------BiPredicate<T,U>---------------
    // ① boolean test(T t, U u)
    // 根据给定的两个输入参数进行判断
    @Test
    public void test51() {
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> !a.equals(b);
        System.out.println(biPredicate.test(1, 2)); // true
    }

    // ② BiPredicate<T,U> and(BiPredicate<? super T,? super U> other)
    // 返回一个组合判断，将other以短路与的方式加入到函数的判断中
    @Test
    public void test52() {
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> !a.equals(b);
        biPredicate = biPredicate.and((a, b) -> a.equals(b));
        System.out.println(biPredicate.test(1, 2)); // false
    }

    // ③ BiPredicate<T,U> or(BiPredicate<? super T,? super U> other)
    // 返回一个组合判断，将other以短路或的方式加入到函数的判断中
    @Test
    public void test53() {
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> !a.equals(b);
        biPredicate = biPredicate.or((a, b) -> a == b);
        System.out.println(biPredicate.test(1, 1)); // true
    }

    // ④ BiPredicate<T,U> negate()
    // 将函数的判断取反
    @Test
    public void test54() {
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> !a.equals(b);
        biPredicate = biPredicate.negate();
        System.out.println(biPredicate.test(1, 2)); // false
    }















    //-----------------------------------Supplier供应商----------------
    //----------------------------------Supplier<T>-----------------
    // ① T get()
    // 获取结果值
    @Test
    public void test61() {
        Supplier<String> supplier = () -> "Hello Jack!";
        System.out.println(supplier.get()); // Hello Jack!
    }
}
