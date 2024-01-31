package com.example.demothread.juc.thread_pool;

import java.util.concurrent.TimeUnit;

/**
 * 从JDK 5开始，把工作单元与执行机制分离开来，工作单元包括Runnable和Callable，而执行机制由Executor框架提供。
 *
 * @author wfh
 * @create 2024/1/31 14:03
 */
public class WorkerThread implements Runnable {

    private String command;

    public WorkerThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
