package com.example.demothread.juc.util.cyclic_barrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 对于CountDownLatch，其他线程为游戏玩家，比如英雄联盟，主线程为控制游戏开始的线程。
 * 在所有的玩家都准备好之前，主线程是处于等待状态的，也就是游戏不能开始。当所有的玩家准备好之后，下一步的动作实施者为主线程，即开始游戏。
 * <p>
 * 对于CyclicBarrier，假设有一家公司要全体员工进行团建活动，活动内容为翻越三个障碍物，每一个人翻越障碍物所用的时间是不一样的。
 * 但是公司要求所有人在翻越当前障碍物之后再开始翻越下一个障碍物，也就是所有人翻越第一个障碍物之后，才开始翻越第二个，
 * 以此类推。类比地，每一个员工都是一个“其他线程”。当所有人都翻越的所有的障碍物之后，程序才结束。而主线程可能早就结束了，这里我们不用管主线程。
 * <p>
 * CountDownLatch和CyclicBarrier都有让多个线程等待同步然后再开始下一步动作的意思，但是CountDownLatch的下一步的动作实施者是主线程，具有不可重复性；
 * 而CyclicBarrier的下一步动作实施者还是“其他线程”本身，具有往复多次实施动作的特点。
 *
 * @author wfh
 * @create 2024/1/31 16:06
 */
public class CyclicBarrierTest {

    static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBar;

        Soldier(CyclicBarrier cyclicBar, String soldier) {
            this.cyclicBar = cyclicBar;
            this.soldier = soldier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵到齐
                cyclicBar.await();
                doWork();
                //等待所有士兵完成工作
                // 当再一次调用 CyclicBarrier.await() 方法时，会进行下一次计数。
                cyclicBar.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":任务完成");
        }
    }

    static class BarrierRun implements Runnable {
        boolean flag;
        int num;

        BarrierRun(boolean flag, int num) {
            this.flag = flag;
            this.num = num;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令:[士兵" + num + "个，任务完成！]");
            } else {
                System.out.println("司令:[士兵" + num + "个，集合完毕！]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int num = 10;
        Thread[] allSoldier = new Thread[num];
        boolean flag = false;
        // 创建了 CyclicBarrier 实例，并将计数器设置为 10 ，要求在计数器达到指标时，执行第 BarrierRun的 run() 方法.
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, new BarrierRun(flag, num));
        //设置屏障点，主要是为了执行这个方法
        System.out.println("集合队伍！");
        for (int i = 0; i < num; i++) {
            System.out.println("士兵 " + i + " 报道！");
            allSoldier[i] = new Thread(new Soldier(cyclicBarrier, "士兵 " + i));
            allSoldier[i].start();
        }
    }

}
