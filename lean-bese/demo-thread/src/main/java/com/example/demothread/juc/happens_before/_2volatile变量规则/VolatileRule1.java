package com.example.demothread.juc.happens_before._2volatile变量规则;

import java.util.concurrent.TimeUnit;

/**
 * updater线程在将stop变量设置为true之后，又对volatileObject变量进行了赋值，而getter线程在读取stop变量之前首先读取了volatileObject。
 * 根据程序次序规则在updater线程内stop = true先行发生于volatileObject = new Object()，在getter线程内Object volatileObject = VolatileRule1.volatileObject先行发生于if (stop)；
 * 再根据传递性则stop = true先行发生于if (stop)，所以stop = true对于if (stop)就是可见的。
 *
 * @author wfh
 * @create 2024/1/30 15:19
 */
public class VolatileRule1 {

    private static boolean stop = false;
    private static volatile Object volatileObject = new Object();


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
                Object volatileObject = VolatileRule1.volatileObject;
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

