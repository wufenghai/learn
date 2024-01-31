package com.example.demothread.juc.util.count_down_latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 用来同步一个或多个任务的常用并发工具类，强制它们等待由其他任务执行的一组操作完成。
 * <p>
 * CountDownLatch典型的用法是将一个程序分为n个互相独立的可解决任务，并创建值为n的CountDownLatch。
 * 当每一个任务完成时，都会在这个锁存器上调用countDown，等待问题被解决的任务调用这个锁存器的await，将他们自己拦住，直至锁存器计数结束。
 *
 * @author wfh
 * @create 2024/1/31 15:48
 */
public class CountDownLatchTest {

    /*
        实现一个容器，提供两个方法，add，size 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束?
     */

    volatile List list = new ArrayList();

    public void add(int i) {
        list.add(i);
    }

    public int getSize() {
        return this.list.size();
    }


    public static void main(String[] args) {
        CountDownLatchTest test = new CountDownLatchTest();
        // 设置门闩数 为1
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start");
            if (test.getSize() != 5) {
                try {
                    System.out.println("t2 await()");
                    latch.await();
                    System.out.println("t2 end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 9; i++) {
                test.add(i);
                System.out.println("add" + i);
                if (test.getSize() == 5) {
                    System.out.println("countdown is open");
                    latch.countDown();
                }
            }
            System.out.println("t1 end");
        }, "t1").start();

    }

}
