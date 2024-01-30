package com.example.demothread.threads.yield;

/**
 * 1、记住当线程的优先级没有指定时，所有线程都携带普通优先级。
 * 2、优先级可以用从 1 到 10 的范围指定。10 表示最高优先级，1 表示最低优先级，5 是普通优先级。
 * 3、记住优先级最高的线程在执行时被给予优先。但是不能保证线程在启动时就进入运行状态。
 * 4、与在线程池中等待运行机会的线程相比，当前正在运行的线程可能总是拥有更高的优先级。
 * 5、由调度程序决定哪一个线程被执行。
 * 6、t.setPriority() 用来设定线程的优先级。
 * 7、记住在线程 start() 方法被调用之前，线程的优先级应该被设定。
 * 8、你可以使用常量，如 MIN_PRIORITY，MAX_PRIORITY，NORM_PRIORITY 来设定优先级。
 *
 * @author wfh
 * @create 2024/1/30 11:26
 */
public class YieldTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable(), "Thread 1");
        Thread thread2 = new Thread(new MyRunnable(), "Thread 2");
        thread1.start();
        thread2.start();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                // 当 i 等于 3 时，调用 yield() 方法让出 CPU 执行权
                if (i == 3) {
                    Thread.yield();
                    System.out.println("当 i 等于 3 时，调用 yield() 方法让出 CPU 执行权");
                }
            }
        }
    }


}
