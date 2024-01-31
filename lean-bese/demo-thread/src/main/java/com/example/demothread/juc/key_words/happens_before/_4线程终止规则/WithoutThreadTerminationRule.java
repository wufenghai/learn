package com.example.demothread.juc.key_words.happens_before._4线程终止规则;

import java.util.concurrent.TimeUnit;

/**
 * @author wfh
 * @create 2024/1/30 15:30
 */
public class WithoutThreadTerminationRule {
    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread updater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                stop = true;
                System.out.println("updater set stop true.");
            }
        });
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
        updater.start();
        getter.start();
    }
}
