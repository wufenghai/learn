package com.example.demothread.juc.key_words.happens_before._2volatile变量规则;

import java.util.concurrent.TimeUnit;

/**
 * 使用Happens-Before原则进行分析，根据volatile变量规则，
 * updater线程对stop变量的写操作先行发生于getter线程对stop变量的读操作，
 * 所以updater线程将stop变量设置为true对getter线程读取stop变量是可见的。
 *
 * @author wfh
 * @create 2024/1/30 15:17
 */
public class VolatileRule {

    private static volatile boolean stop = false;

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
