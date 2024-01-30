package com.example.demothread.juc.volatiles;

/**
 * Volatile关键字的作用主要有如下两个：
 * 1.线程的可见性：当一个线程修改一个共享变量时，另外一个线程能读到这个修改的值。
 * 2. 顺序一致性：禁止指令重排序。
 *
 * @author wfh
 * @create 2024/1/30 11:12
 */
public class VolatileTest {

    //    boolean flag = true;   如果没有使用volatile 线程t1会一直执行，他不会感觉到t2已经修改了flag的值
    volatile boolean flag = true;

    public void updateFlag() {
        this.flag = false;
        System.out.println("flag 值修改了: " + this.flag);
    }

    // 线程可见性的实例
    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        new Thread(() -> {
            while (test.flag) {
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                test.updateFlag();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

    }


}
