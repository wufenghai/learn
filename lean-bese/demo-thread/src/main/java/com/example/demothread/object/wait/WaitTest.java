package com.example.demothread.object.wait;

/**
 * @author wfh
 * @create 2024/1/30 13:35
 */
public class WaitTest {

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            Object lock = new Object();
            synchronized (lock) {
                try {
                    System.out.println("In ThreadA begin at " + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("In ThreadA end at " + System.currentTimeMillis());
                } catch (InterruptedException e) {

                }
            }
        }
    }

    static class ThreadB extends Thread {

        public void run() {
            Object lock = new Object();
            synchronized (lock) {
                try {
                    System.out.println("In ThreadB begin at " + System.currentTimeMillis());
                    lock.wait(2000);
                    System.out.println("In ThreadB end at " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
