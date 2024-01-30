package com.example.demothread.threads.interrupt;

/**
 * interrupt（）
 *  其作用是中断此线程（此线程不一定是当前线程，而是指调用该方法的Thread实例所代表的线程），
 *  但实际上只是给线程设置一个中断标志，线程仍会继续运行。）
 *  作用与正常线程会将中断标记设置为true,但是作用于阻塞线程会将中断标志刷新false（中断标记默认为false，刷新就是重新刷会默认）。
 * <p>
 * interrupted（）
 *  作用是测试当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态，第二次再调用时中断状态已经被清除，将返回一个false。
 *  InterruptedException异常会清除interrupted status
 * <p>
 * isInterrupted（）
 *  作用是只测试此线程是否被中断 ，不清除中断状态。
 *
 * @author wfh
 * @create 2024/1/29 15:31
 */
public class InterruptExample {

    public static class MyThread1 extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("第一次获取中断标志 : " + interrupted());//true
                System.out.println("第二次获取中断标志 : " + interrupted());//false
                //该线程处于阻塞,所以会报错
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new MyThread1();
        thread1.start();
        // InterruptedException
        // 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束该线程。
        // 但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
        thread1.interrupt();
        System.out.println("Main run");
        System.out.println(thread1.isInterrupted());
    }
}
