package com.example.demothread.juc.key_words.happens_before._3线程启动规则;

import java.util.concurrent.TimeUnit;

/**
 *
 * 根据程序次序规则在updater线程内stop = true先行发生于getter.start()；
 * 而根据线程启动规则getter.start() 先行发生于该线程内的每一个动作（包括if (stop)）；
 * 再根据传递性规则stop = true先行发生于if (stop)，所以stop = true对if (stop)是可见的。
 * @author wfh
 * @create 2024/1/30 15:28
 */
public class ThreadStartRule {
    private static boolean stop = false;

    public static void main(String[] args) {
        Thread getter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
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
                stop = true;
                System.out.println("updater set stop true.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                getter.start();
                while (true){
                }
            }
        });
        updater.start();
    }
}
