package com.example.demothread.juc.thread_pool._自定义拒绝策略;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 我们也可以限制线程池的大小并且创建我们自己的 RejectedExecutionHandler 实现来处理不能适应工作队列的工作。
 * <p>
 * AbortPolicy: 直接抛出异常，默认策略；
 * DiscardPolicy: 直接丢弃任务；
 * DiscardOldestPolicy: 丢弃阻塞队列中靠最前的任务，并执行当前任务；
 * CallerRunsPolicy: 用调用者所在的线程来执行任务；
 *
 * @author wfh
 * @create 2024/1/31 14:14
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " is rejected");
    }
}
