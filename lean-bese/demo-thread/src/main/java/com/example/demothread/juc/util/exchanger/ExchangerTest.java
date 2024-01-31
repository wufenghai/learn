package com.example.demothread.juc.util.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Exchanger用于进行两个线程之间的数据交换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。
 * 这两个线程通过exchange()方法交换数据，当一个线程先执行exchange()方法后，
 * 它会一直等待第二个线程也执行exchange()方法，当这两个线程到达同步点时，这两个线程就可以交换数据了。
 * <p>
 * 可以和之前分析过的SynchronousQueue对比一下：
 * 线程A通过SynchronousQueue将数据a交给线程B；
 * 线程A通过Exchanger和线程B交换数据，线程A把数据a交给线程B，同时线程B把数据b交给线程A。
 * 可见，SynchronousQueue是交给一个数据，Exchanger是交换两个数据
 *
 * @author wfh
 * @create 2024/1/31 17:29
 */
public class ExchangerTest {

    /*
        你有相同的数据buffer，一个或多个数据生产者，和一个或多个数据消费者。
        只是Exchange类只能同步2个线程，所以你只能在你的生产者和消费者问题中只有一个生产者和一个消费者时使用这个类。
     */
    static class Producer extends Thread {
        private Exchanger<Integer> exchanger;
        private static int data = 0;

        Producer(String name, Exchanger<Integer> exchanger) {
            super("Producer- " + name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    data = i;
                    System.out.println(getName() + " 交换前:" + data);
                    data = exchanger.exchange(data);
                    System.out.println(getName() + " 交换后:" + data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private Exchanger<Integer> exchange;
        private static int data = 0;

        Consumer(String name, Exchanger<Integer> exchange) {
            super("Consumer- " + name);
            this.exchange = exchange;
        }

        @Override
        public void run() {
            while (true) {
                data = 0;
                System.out.println(getName() + " 交换前:" + data);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    data = exchange.exchange(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(getName()+" 交换后:" + data);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<Integer>();
        new Producer("", exchanger).start();
        new Consumer("", exchanger).start();
        TimeUnit.SECONDS.sleep(7);
        System.exit(-1);
    }


}
