package com.example.demothread.juc.happens_before._1管程锁定规则;

import java.util.concurrent.TimeUnit;

/**
 * 结合Happens-Before原则进行分析，根据程序次序规则在updater线程内stop = true先行发生于lockObject锁的释放，
 * 在getter线程内lockObject锁的获取先行发生于if (stop)；再根据传递性则stop = true先行发生于if (stop)，所以stop = true对于if (stop)是可见的。
 *
 * @author wfh
 * @create 2024/1/30 15:01
 */
public class MonitorLockRuleSynchronized {

    private static boolean stop = false;
    // 间接证明 synchronized是包含可见性的
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread updater = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock) {
                stop = true;
                System.out.println("updater set stop true.");
            }
        }, "updater");

        Thread getter = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (stop) {
                        System.out.println("getter stopped.");
                        break;
                    }
                }
            }
        }, "getter");
        getter.start();
        updater.start();

    }

}
