package com.example.demothread.juc.happens_before._3线程启动规则;

import java.util.concurrent.TimeUnit;

/**
 * getter线程未输出getter stopped，说明updater线程对stop变量的修改对getter线程不可见。
 *
 * @author wfh
 * @create 2024/1/30 15:25
 */
public class WithoutThreadStartRule {
    private static boolean stop = false;

    public static void main(String[] args) {
        Thread getter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (WithoutThreadStartRule.stop) {
                        System.out.println("getter stopped.");
                        break;
                    }
                }
            }
        }, "getter");

        Thread updater = new Thread(new Runnable() {
            @Override
            public void run() {
                getter.start();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                stop = true;
                System.out.println("updater set stop true.");
                while (true) {
                }
            }
        });
        updater.start();
    }
}

