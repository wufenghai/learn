package com.example.learn.data.structure.from.net.array;

import java.util.Arrays;

/**
 * 动态数组
 *
 * @author wfh
 * @create 2023/5/5 15:09
 */
public class DynamicArray<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     *
     * @param capacity
     */
    public DynamicArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;

    }

    /**
     * 无参数的构造函数，默认数组的容量capacity=10
     */
    public DynamicArray() {
        this(10);
    }

    /**
     * 获取数组中的元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }


    /**
     * 获取数组的容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 返回数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向所有元素后添加一个新元素e
     * 时间复杂度：O(1)
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向所有元素前添加一个新元素e
     * 时间复杂度：O(n)
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在第index个位置插入一个新元素e
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("无效添加 index >=0 ");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引出错");
        }
        return data[index];
    }

    /**
     * 修改index索引位置的元素为e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引出错");
        }
        data[index] = e;
    }

    /**
     * 查找数据中是否有元素e
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在则返回索引为-1
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }
        E temp = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //loireting objects !=memory leak,不写的话逻辑上也正确
        data[index] = null;
        //防止复杂度震荡，所以1/4  防止删到最后一位
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return temp;
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     * 1. 可以返回boolean  2.重复数组的考量，删除所有的元素e,设计上的考虑，具体需求具体分析
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index == -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("DynamicArray: size=%d , capacity=%d \n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 扩容
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }



}
