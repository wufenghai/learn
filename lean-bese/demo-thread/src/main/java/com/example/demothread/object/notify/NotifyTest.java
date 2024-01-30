package com.example.demothread.object.notify;

/**
 * @author wfh
 * @create 2024/1/30 13:36
 */
public class NotifyTest {

    public static void main(String[] args) {
        Object lock = new Object();
        new ThreadA(lock).start();
        new ThreadB(lock).start();
    }

    static class ThreadA extends Thread {
        Object lock;

        ThreadA(Object lock) {
            this.lock = lock;
        }

        public void run() {
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
        Object lock;

        ThreadB(Object lock) {
            this.lock = lock;
        }

        public void run() {
            synchronized (lock) {
                System.out.println("In ThreadB begin at " + System.currentTimeMillis());
                lock.notify();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                System.out.println("In ThreadB end at " + System.currentTimeMillis());
            }
        }
    }


}
