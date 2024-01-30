package com.example.demothread.threads.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 用来让其（这里暂称之为子线程）随着调用它的主线程（这里暂称之为main方法）的结束而结束，
 * 不管该线程任务是否圆满完成，只要调用它的主线程结束了，它（子线程）就跟随着结束。
 *
 * @author wfh
 * @create 2024/1/30 11:50
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----我是子线程，每隔一秒打印一次，就是玩儿-----");
            }
        }, "thread-daemon");
        daemon.setDaemon(true);//true 确保主方法结束时候，子线程随之结束（默认是false）
        daemon.start();
        try {
            //让主线程休息一会儿，给子线程充足的执行时间，否则可能造成因主线程执行速度过快，主线程结束了子线程还没来得及执行
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程已结束");
        //只有new(新创建)和terminated(死亡)状态的线程下的isAlive()返回值才是false
        //其他waiting、blocked、runable、running状态下，isAlive()返回值都是true
        System.out.println("子线程状态：" + daemon.getState());
        System.out.println("是否依然存活：" + daemon.isAlive());
        System.out.println("是否是守护线程：" + daemon.isDaemon());

    }

}
