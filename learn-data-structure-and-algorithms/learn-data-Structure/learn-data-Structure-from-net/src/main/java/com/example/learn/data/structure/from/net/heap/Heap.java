package com.example.learn.data.structure.from.net.heap;

/**
 * @author wfh
 * @create 2023/5/7 16:21
 */
public class Heap<T extends Comparable<T>> {
    //用来存储元素的数组
    private T[] items;
    //记录堆中元素的个数
    private int N;
    //创建容量为capacity的heap对象
    public Heap(int capacity){
        this.items= (T[]) new Comparable[capacity+1];
        this.N=0;
    }
    //堆的大小
    public int size(){
        return N;
    }
    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i,int j){
        return items[i].compareTo(items[j])<0;
    }
    //交换堆中i索引和j索引处的值
    private void exch(int i,int j){
        T t = items[i];
        items[i]=items[j];
        items[j]=t;
    }
    //删除堆中最大的元素，并返回这个最大元素
    public T delMax(){
        //取开始的第一个
        T max=items[1];
        //交换索引1处的元素和最大索引处的元素，让完全二叉树中最右侧的元素变为临时根结点
        exch(1,N);
        //最大索引处的元素删掉
        items[N]=null;
        //元素个数减1
        N--;
        //通过下沉调整堆，让堆重新有序
        sink(1);
        return max;
    }
    //往堆中插入一个元素
    public void insert(T t){
        items[++N]=t;//数组索引0处不存值，从索引1开始存值
        swim(N);//将结点处于一个正确的位置

    }
    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k){
        //通过循环，不断的比较当前结点的值和其父结点的值，如果发现父结点的值比当前结点的值小，则交换位置
        while(k>1){
            //比较当前的结点和其父节点
            if(less(k/2,k)){
                exch(k/2,k);
            }
            k=k/2;
        }
    }
    //使用下浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k){
        //通过循环不断的对比当前k结点和其左结点2*k以及右子结点2k+1处中较大值的元素大小，如果当前结点小，则需要交换位置
        while(2*k<=N){
            //获取当前结点的子结点中的较大结点
            int max;//记录较大结点所在的索引
            if(2*k+1<=N){
                if(less(2*k,2*k+1)){
                    max=2*k+1;
                }else{
                    max=2*k;
                }
            }else{
                max=2*k;
            }
            //比较当前结点和较大结点的值
            if(!less(k,max)){
                break;
            }
            //交换K索引处的值和Max索引处的值
            exch(k,max);
            //变换k的值
            k=max;
        }
    }
}