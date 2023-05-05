package com.example.learn.data.structure.from.net.array;

/**
 * @author wfh
 * @create 2023/5/5 16:02
 */
public class DynamicArrayTest {
    private String name;
    private String age;

    public DynamicArrayTest(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, age: %s)", name, age);
    }

    public static void main(String[] args) {
        DynamicArray<DynamicArrayTest> dynamicArrayTest = new DynamicArray<>();
        dynamicArrayTest.addLast(new DynamicArrayTest("sangsan", "21"));
        dynamicArrayTest.addLast(new DynamicArrayTest("lisi", "22"));
        dynamicArrayTest.addLast(new DynamicArrayTest("wangwu", "23"));
        System.out.println(dynamicArrayTest);
    }
}
