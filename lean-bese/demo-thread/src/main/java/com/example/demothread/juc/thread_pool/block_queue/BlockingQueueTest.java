package com.example.demothread.juc.thread_pool.block_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author wfh
 * @create 2024/1/31 11:25
 */
public class BlockingQueueTest {

    /*
        ArrayBlockingQueue: 基于数组结构的有界阻塞队列，按FIFO排序任务；
        LinkedBlockingQueue: 基于链表结构的阻塞队列，按FIFO排序任务，吞吐量通常要高于ArrayBlockingQueue；
        SynchronousQueue: 一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue；
        PriorityBlockingQueue: 具有优先级的无界阻塞队列
     */
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockQueue = new ArrayBlockingQueue(2);
        Producer producer = new Producer(blockQueue);
        Consumer consumer = new Consumer(blockQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(1000);
    }

    static class Producer implements Runnable {
        protected BlockingQueue blockQueue = null;

        public Producer(BlockingQueue blockQueue) {
            this.blockQueue = blockQueue;
        }

        @Override
        public void run() {
            try {
                blockQueue.put("1");
//                Thread.sleep(1000);
                blockQueue.put("2");
//                Thread.sleep(1000);
                blockQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {

        protected BlockingQueue blockQueue = null;

        public Consumer(BlockingQueue blockQueue) {
            this.blockQueue = blockQueue;
        }

        @Override
        public void run() {
            try {
                System.out.println(blockQueue.take());
                Thread.sleep(1000);
                System.out.println(blockQueue.take());
                Thread.sleep(1000);
                System.out.println(blockQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
