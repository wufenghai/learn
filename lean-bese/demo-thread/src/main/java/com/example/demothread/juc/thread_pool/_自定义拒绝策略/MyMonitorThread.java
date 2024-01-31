package com.example.demothread.juc.thread_pool._自定义拒绝策略;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPoolExecutor 提供了一些方法，我们可以使用这些方法来查询 executor 的当前状态，线程池大小，活动线程数量以及任务数量。
 * 因此我是用来一个监控线程在特定的时间间隔内打印 executor 信息。
 *
 * @author wfh
 * @create 2024/1/31 14:16
 */
public class MyMonitorThread implements Runnable {

    private ThreadPoolExecutor executor;

    private int seconds;

    private boolean run = true;

    public MyMonitorThread(ThreadPoolExecutor executor, int dalay) {
        this.executor = executor;
        this.seconds = dalay;
    }

    public void shutdown() {
        this.run = false;
    }

    @Override
    public void run() {

        while (true) {
            System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                            this.executor.getPoolSize(),
                            this.executor.getCorePoolSize(),
                            this.executor.getActiveCount(),
                            this.executor.getCompletedTaskCount(),
                            this.executor.getTaskCount(),
                            this.executor.isShutdown(),
                            this.executor.isTerminated()));
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
