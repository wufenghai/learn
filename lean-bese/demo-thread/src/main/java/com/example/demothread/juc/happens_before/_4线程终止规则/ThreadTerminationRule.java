package com.example.demothread.juc.happens_before._4线程终止规则;

import java.util.concurrent.TimeUnit;

/**
 * 根据线程终止规则updater线程中的所有操作（包括stop = true）先行发生于getter线程调用updater.join()等待updater结束；
 * 根据程序次序规则在getter线程内updater.join()先行发生于if (stop)；
 * 再根据传递性得出stop = true先行发生于if (stop)，所以stop = true对if (stop)是可见的。
 *
 * @author wfh
 * @create 2024/1/30 15:31
 */
public class ThreadTerminationRule {
    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
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
        });
        Thread getter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    updater.join();
                } catch (InterruptedException e) {

                }
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

