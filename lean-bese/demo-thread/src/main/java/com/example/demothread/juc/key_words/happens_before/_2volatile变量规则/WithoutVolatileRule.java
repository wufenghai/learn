package com.example.demothread.juc.key_words.happens_before._2volatile变量规则;

import java.util.concurrent.TimeUnit;

/**
 * @author wfh
 * @create 2024/1/30 15:16
 */
public class WithoutVolatileRule {

    private static boolean stop = false;

    public static void main(String[] args) {
        Thread updater = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                stop = true;
                System.out.println("updater set stop true");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "updater");

        Thread getter = new Thread(() -> {
            while (true) {
                if (stop) {
                    System.out.println("getter stopped");
                    break;
                }
            }
        }, "getter");

        //getter线程未输出getter stopped，说明updater线程对stop变量的修改对getter线程不可见。
        getter.start();
        updater.start();
    }

}
