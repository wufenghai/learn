package com.example.demothread.juc.thread_pool.future_task;

import java.util.concurrent.*;

/**
 * @author wfh
 * @create 2024/1/31 11:58
 */
public class FutureTaskTest {

    public static void main(String[] args) {

//        //第一种方式:Future + ExecutorService
//        Task task1 = new Task();
//        ExecutorService service1 = Executors.newCachedThreadPool();
//        Future<Integer> future1 = service1.submit(task1);
//        service1.shutdown();

        // 第二种方式: FutureTask + ExecutorService
//        ExecutorService service2 = Executors.newCachedThreadPool();
//        Task task2 = new Task();
//        FutureTask<Integer> future2 = new FutureTask<>(task2);
//        service2.submit(task2);
//        service2.shutdown();

        // 第三种方式:FutureTask + Thread
        // 新建FutureTask,需要一个实现了Callable接口的类的实例作为构造函数参数
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        // 新建Thread对象并启动
        Thread thread = new Thread(futureTask);
        thread.setName("Task thread");
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");

        // 调用isDone()判断任务是否结束
        if (!futureTask.isDone()){
            System.out.println("Task is not done");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int result = 0;
        try {
            // 调用get()方法获取任务结果,如果任务没有执行完成则阻塞等待
            result = futureTask.get();
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);

    }

    // 1. 继承Callable接口,实现call()方法,泛型参数为要返回的类型
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
            int result = 0;
            for (int i = 0; i < 100; ++i) {
                result += i;
            }

            Thread.sleep(3000);
            return result;
        }
    }
}
