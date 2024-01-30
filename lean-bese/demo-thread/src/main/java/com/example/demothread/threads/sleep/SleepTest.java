package com.example.demothread.threads.sleep;

/**
 * @author wfh
 * @create 2024/1/30 11:39
 */
public class SleepTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 线程执行 sleep（）");
                // 休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t1");
        t1.start();
        Thread.sleep(1000);
        System.out.println("t1 线程当前状态："+t1.getState());
    }
}
