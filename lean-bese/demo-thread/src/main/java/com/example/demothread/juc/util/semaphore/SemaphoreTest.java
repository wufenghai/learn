package com.example.demothread.juc.util.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore称为计数信号量，它允许n个任务同时访问某个资源，可以将信号量看做是在向外分发使用资源的许可证，只有成功获取许可证，才能使用资源
 * <p>
 * Semaphore的基本使用场景是限制一定数量的线程能够去执行.
 *
 * @author wfh
 * @create 2024/1/31 16:28
 */
public class SemaphoreTest {

    /*
        第一步: 首先线程0和1, 获取锁. 线程3被被阻塞.
        第二步: 3秒过后, 线程0和线程1分别释放锁,
        第三步: 线程2可以获得到锁.
     */
    public static void main(String[] args) {
        // 表示有2个许可
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
//                    semaphore.release();  不管release在哪里调用，他都会增加一个信号量
                    // 默认使用一个许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread() + " I get it.");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread() + " I release it.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
