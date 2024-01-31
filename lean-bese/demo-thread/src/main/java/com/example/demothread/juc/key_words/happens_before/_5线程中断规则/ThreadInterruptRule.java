package com.example.demothread.juc.key_words.happens_before._5线程中断规则;

import java.util.concurrent.TimeUnit;

/**
 * 根据程序次序规则在updater线程内stop = true先行发生于getter.interrupt()，在getter线程内Thread.currentThread().isInterrupted() 先行发生于if (stop)；
 * 根据线程中断规则getter.interrupt()先行发生于Thread.currentThread().isInterrupted()；
 * 再根据传递性得出stop = true先行发生于if (stop)，所以stop = true对于if (stop)是可见的。
 *
 * @author wfh
 * @create 2024/1/30 15:36
 */
public class ThreadInterruptRule {
    private static boolean stop = false;

    public static void main(String[] args) {
        Thread getter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        if (stop) {
                            System.out.println("getter stopped.");
                            break;
                        }
                    }
                }
            }
        }, "getter");
        Thread updater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                stop = true;
                getter.interrupt();
                System.out.println("updater set stop true.");
            }
        }, "updater");

        updater.start();
        getter.start();
    }
}
