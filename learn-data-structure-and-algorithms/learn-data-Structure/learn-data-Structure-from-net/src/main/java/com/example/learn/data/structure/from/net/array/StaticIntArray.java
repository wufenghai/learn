package com.example.learn.data.structure.from.net.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 静态整型数组
 *
 * @author wfh
 * @create 2023/5/5 13:59
 */
public class StaticIntArray {

    /**
     * 定义一个基础数组，用来存放数据。
     */
    private int[] data;

    /**
     * 用记录数组中的数据个数。
     */
    private int size;

    //----------------------------------------------------基础操作----------------------------------------------------------------

    /**
     * 构造函数，实例化的时候需要指定一个容量capacity对数组进行初始化
     *
     * @param capacity
     */
    public StaticIntArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    /**
     * 无参构造，实例化时不指定容量将默认为10
     */
    public StaticIntArray() {
//        data = new int[10];
//        size = 0;
        this(10);// 调用有参构造函数
    }

    /**
     * 获取数组中元素的个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     */
    public int getlength() {
        return data.length;
    }

    /**
     * 判断当前数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    //----------------------------------------------------增删改查----------------------------------------------------------------

    /**
     * 向数组中索引为index的位置插入一个元素
     *
     * @param index
     * @param e
     */
    public void add(int index, int e) {
        //如果数组已经满了，就抛出异常，添加失败
        if (size == data.length) {
            throw new IllegalArgumentException("数组已经满了");
        }
        //如果索引值小于0或者索引值大于了数组元素的个数，抛异常
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("索引出错");
        }
        //将index位置开始的元素向后移动一格
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        //将e插入到index索引位上
        data[index] = e;
        //维护元素个数+1
        size++;
    }

    /**
     * 一个将元素e快速插入数组头部方法
     *
     * @param e
     */
    public void addFirst(int e) {
        add(0, e);
    }

    /**
     * 一个将元素e快速插入数组尾部的方法
     *
     * @param e
     */
    public void addLast(int e) {
        add(size, e);
    }

    /**
     * 查询索引为index位置的元素e
     *
     * @param index
     * @return
     */
    public int get(int index) {
        //如果索引值小于0或者索引值大于了数组元素的个数，参数不合法，抛异常
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("索引失效");
        }
        return data[index];
    }

    /**
     * 查询数组中是否存在元素e
     *
     * @param e
     * @return
     */
    public boolean isExist(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询元素e在数组中的位置
     *
     * @param e
     * @return
     */
    public int findIndex(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        // 没有找到元素e就返回-1
        return -1;
    }

    /**
     * 从数组中移除索引为index位置的元素，并将该元素返回
     *
     * @param index
     * @return
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引失效");
        }
        int temp = data[index];
        //从index+1开始往前覆盖数组
        for (int i = index + 1; size > i; i++) {
            data[index - 1] = data[i];
        }
        size--;
        data[index] = 0;
        return temp;
    }

    /**
     * 一个快速删除头部元素的方法
     *
     * @return
     */
    public int removeFirst() {
        return remove(0);
    }

    /**
     * 一个快速删除尾部元素的方法
     */
    public int removeLast() {
        return remove(size);
    }

    /**
     * 从数组中删除元素e
     *
     * @param e
     * @return
     */
    public int removeElement(int e) {
        int index = findIndex(e);
        if (index != -1) {
            return remove(index);
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("StaticIntArray: size = %d, length = %d \n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {

        StaticIntArray arr = new StaticIntArray(8);
        // 从数组尾部依次插入0-7
        for (int i = 0; i < 8; i++) {
            arr.addLast(i);
        }
        System.out.println(arr.toString());

        arr.remove(5);
        System.out.println(arr.toString());

        arr.removeElement(2);
        System.out.println(arr.toString());

    }


}
