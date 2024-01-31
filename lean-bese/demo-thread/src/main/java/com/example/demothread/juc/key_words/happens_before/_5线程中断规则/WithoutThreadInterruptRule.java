package com.example.demothread.juc.key_words.happens_before._5线程中断规则;

import java.util.concurrent.TimeUnit;

/**
 * @author wfh
 * @create 2024/1/30 15:35
 */
public class WithoutThreadInterruptRule {
    private static boolean stop = false;

    public static void main(String[] args) {
        Thread getter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (stop) {
                        System.out.println("getter stopped.");
                        break;
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
                System.out.println("updater set stop true.");
            }
        }, "updater");

        updater.start();
        getter.start();
    }
}

