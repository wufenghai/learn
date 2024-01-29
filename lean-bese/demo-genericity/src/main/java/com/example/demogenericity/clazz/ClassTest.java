package com.example.demogenericity.clazz;

/**
 * @author wfh
 * @create 2024/1/29 9:44
 */
public class ClassTest<T> {  //此处可以随便写标识符号，T是type的简称

    private T var;  //var的类型由T指定，即：由外部指定

    public T getVar(){ // 返回值的类型有外部决定
        return var;
    }

    public void setVar(T var) {  // 设置的类型也由外部决定
        this.var = var;
    }

    public static void main(String[] args) {
        ClassTest<Integer> classInteger = new ClassTest<>();
        classInteger.setVar(1);
        System.out.println(classInteger.getVar());

        ClassTest<String> classString = new ClassTest<>();
        classString.setVar("123");
        System.out.println(classString.getVar());

    }


}
