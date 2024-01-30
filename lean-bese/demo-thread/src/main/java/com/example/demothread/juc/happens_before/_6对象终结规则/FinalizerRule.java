package com.example.demothread.juc.happens_before._6对象终结规则;

/**
 * 根据程序次序规则在main线程中stop = true先行发生于Test的构造方法结束；
 * 根据对象终结规则Test的构造方法结束先行发生于Test的finalize()方法的开始；
 * 再根据传递性得出stop = true先行发生于finalize()方法的开始，所以stop = true对于finalize()方法是可见的。
 *
 * @author wfh
 * @create 2024/1/30 15:39
 */
public class FinalizerRule {
    private static boolean stop = false;

    public static void main(String[] args) {
        Test test = new Test();
        //test设置为null以后就可以回收了
        test = null;
        while (true) {
            //促使垃圾回收
            byte[] bytes = new byte[1024 * 1024];
        }
    }

    static class Test {
        public Test() {
            stop = true;
            System.out.println("set stop true in constructor");
        }

        @Override
        protected void finalize() throws Throwable {
            if (stop) {
                System.out.println("stop true in finalize, threadName  " + Thread.currentThread().getName());
            } else {
                System.out.println("stop false in finalize, threadName " + Thread.currentThread().getName());
            }
        }
    }
}

