package com.example.learn.data.structure.from.net.heap;

/**
 * @author wfh
 * @create 2023/5/7 16:23
 */
public class HeapTest {
    public static void main(String[] args) {
        //创建堆对象
        Heap<String> heap = new Heap<>(10);
        //往堆中存入字符串数据
        heap.insert("A");
        heap.insert("B");
        heap.insert("C");
        heap.insert("D");
        heap.insert("E");
        heap.insert("F");
        heap.insert("G");

        //通过循环从堆中删除数据
        String result = null;
        while((result = heap.delMax())!=null){
            //逐个将堆中的大的元素从堆中删除，并打印
            System.out.print(result+" ");
        }
    }
}
