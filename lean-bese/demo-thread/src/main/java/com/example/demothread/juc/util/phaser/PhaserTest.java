package com.example.demothread.juc.util.phaser;

/**
 * @author wfh
 * @create 2024/1/31 17:18
 */
public class PhaserTest {

    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();
        StudentTask[] studentTasks = new StudentTask[5];
        for (int i = 0; i < studentTasks.length; i++) {
            studentTasks[i] = new StudentTask(myPhaser);
            //注册一次表示phaser维护的线程个数
            myPhaser.register();
        }
        Thread[] threads = new Thread[studentTasks.length];
        for (int i = 0; i < studentTasks.length; i++) {
            threads[i] = new Thread(studentTasks[i], "Student " + i);
            threads[i].start();
        }

        //等待所有线程执行结束
        for (int i = 0; i < studentTasks.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Phaser has finished:" + myPhaser.isTerminated());


    }


}
