package com.example.demothread.juc.thread_pool._自定义拒绝策略;

import com.example.demothread.juc.thread_pool.WorkerThread;

import java.util.concurrent.*;

/**
 * @author wfh
 * @create 2024/1/31 14:22
 */
public class WorkerPool {

    public static void main(String[] args) throws InterruptedException {
        // 自定义拒绝策略
        RejectedExecutionHandlerImpl rejectedHandler = new RejectedExecutionHandlerImpl();
        // 创建线程的工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        /*
            注意在初始化 ThreadPoolExecutor 时，我们保持初始池大小为 2，最大池大小为 4 而工作队列大小为 2。
            因此如果已经有四个正在执行的任务而此时分配来更多任务的话，工作队列将仅仅保留他们(新任务)中的两个，
            其他的将会被 RejectedExecutionHandlerImpl 处理。
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                threadFactory,
                rejectedHandler);
        // 监控线程
        MyMonitorThread monitor = new MyMonitorThread(threadPoolExecutor, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        for (int i = 0; i < 10; i++) {
            // 工作线程
            threadPoolExecutor.execute(new WorkerThread("cmd" + i));
        }

        Thread.sleep(30000);
        threadPoolExecutor.shutdown();
        Thread.sleep(5000);
        monitor.shutdown();

    }
}
