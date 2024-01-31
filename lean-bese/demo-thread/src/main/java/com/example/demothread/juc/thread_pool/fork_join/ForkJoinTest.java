package com.example.demothread.juc.thread_pool.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wfh
 * @create 2024/1/31 15:18
 */
public class ForkJoinTest {

    static final class SumTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 1L;

        final int start;//开始计算的数
        final int end; // 最后计算的数

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            // 如果计算量小于1000， 那么分配一个线程执行if 中的代码块，并返回执行结果
            if (end - start < 1000) {
                System.out.println(Thread.currentThread().getName() + " 开始执行: " + start + "-" + end);
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
            // 如果计算量大于 1000 ， 那么拆分为两个任务
            SumTask task1 = new SumTask(start, start + ((end - start) >> 1));
            SumTask task2 = new SumTask(start + ((end - start) >> 1) + 1, end);
            //执行任务
            task1.fork();
            task2.fork();
            //获取任务执行的结果
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = new SumTask(1, 10000);
        pool.submit(task);
        System.out.println(task.get());
    }
}