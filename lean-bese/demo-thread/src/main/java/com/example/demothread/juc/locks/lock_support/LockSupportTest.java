package com.example.demothread.juc.locks.lock_support;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wfh
 * @create 2024/1/30 17:40
 */
public class LockSupportTest {

    static class MyThread extends Thread {
        private Object object;

        public MyThread(Object object) {
            this.object = object;
        }

        @Override
        public void run() {
            System.out.println("before unpark");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 获取blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
            // 释放许可
            LockSupport.unpark((Thread) object);
            // 休眠500ms，保证先执行park中的setBlocker(t,null);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //再次获取blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
            System.out.println("after unpark");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");
        // 获取许可
        LockSupport.park("LockSupportTest");
        System.out.println("after park");
    }
}

