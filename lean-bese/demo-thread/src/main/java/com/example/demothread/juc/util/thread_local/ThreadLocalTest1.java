package com.example.demothread.juc.util.thread_local;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 为什么ThreadLocal会造成内存泄露? 如何解决
 * <p>
 * 如果用线程池来操作ThreadLocal 对象确实会造成内存泄露, 因为对于线程池里面不会销毁的线程,
 * 里面总会存在着<ThreadLocal, LocalVariable>的强引用,
 * 因为final static 修饰的 ThreadLocal 并不会释放, 而ThreadLocalMap 对于 Key 虽然是弱引用,
 * 但是强引用不会释放, 弱引用当然也会一直有值, 同时创建的LocalVariable对象也不会释放, 就造成了内存泄露;
 * 如果LocalVariable对象不是一个大对象的话, 其实泄露的并不严重,
 * 泄露的内存 = 核心线程数 * LocalVariable对象的大小;
 *
 * @author wfh
 * @create 2024/1/31 18:02
 */
public class ThreadLocalTest1 {

    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    // (1)
    final static ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
            5,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    // (2)
    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    public static void main(String[] args) throws InterruptedException {
        // (3)
        Thread.sleep(5000 * 4);
        for (int i = 0; i < 50; ++i) {
            pool.execute(() -> {
                // (4)
                localVariable.set(new LocalVariable());
                // (5)
                System.out.println("use local varaible" + localVariable.get());
                // 为了避免出现内存泄露的情况, ThreadLocal提供了一个清除线程中对象的方法, 即 remove, 其实内部实现就是调用 ThreadLocalMap 的remove方法:
                // 找到Key对应的Entry, 并且清除Entry的Key(ThreadLocal)置空, 随后清除过期的Entry即可避免内存泄露。
                localVariable.remove();
            });
        }
        // (6)
        System.out.println("pool execute over");
    }


}
