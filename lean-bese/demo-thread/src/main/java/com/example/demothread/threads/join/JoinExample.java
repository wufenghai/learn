package com.example.demothread.threads.join;

/**
 * join方法，意思就是在自己当前线程加入你调用的线程，本线程则等待，当调用Joni的线程运行完了后，自己再去运行，
 * t1和t2两个线程，如果在t1线程里面调用了t2.join()，则t1线程会进行等待状态，t2运行结束以后，才会继续运行t1。这样可以保证线程的先后顺序。
 *
 * @author wfh
 * @create 2024/1/29 15:44
 */
public class JoinExample {

    private static class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    private static class B extends Thread {
        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                /*
                虽然 b 线程先启动，但是因为在 b 线程中调用了 a 线程的 join() 方法，b 线程会等待 a 线程结束才继续执行，
                因此最后能够保证 a 线程的输出先于 b 线程的输出。
                 */
                a.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}
