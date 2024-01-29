package com.example.demogenericity.clazz;

/**
 * @author wfh
 * @create 2024/1/29 9:51
 */
public class MultiClassTest<K, V> { //此处指定了两个泛型类型

    private K key; //次变量的类型由外部决定

    private V value; //次变量的类型由外部决定

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public static void main(String[] args) {
        MultiClassTest<String, Integer> test = new MultiClassTest<>();
        test.setKey("www");
        test.setValue(456);
        System.out.println(test.getKey() + ":" + test.getValue());

    }

}
