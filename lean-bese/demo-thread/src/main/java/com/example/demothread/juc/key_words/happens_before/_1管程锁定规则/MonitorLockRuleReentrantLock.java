package com.example.demothread.juc.key_words.happens_before._1管程锁定规则;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  ReentrantLock也可以起到和Synchronized关键字同样的效果
 *
 * @author wfh
 * @create 2024/1/30 15:13
 */
public class MonitorLockRuleReentrantLock {

    private static boolean stop = false;

    private static ReentrantLock lock = new ReentrantLock();

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
