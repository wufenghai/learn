package com.example.demothread.juc.util.phaser;

import java.util.concurrent.Phaser;

/**
 * Phaser表示“阶段器”，用来解决控制多个线程分阶段共同完成任务的情景问题。
 * <p>
 * 在Phaser内有2个重要状态，分别是phase和party。
 * phase就是阶段，初值为0，当所有的线程执行完本轮任务，同时开始下一轮任务时，意味着当前阶段已结束，进入到下一阶段，phase的值自动加1。
 * party就是线程，party=4就意味着Phaser对象当前管理着4个线程。
 * Phaser还有一个重要的方法经常需要被重载，那就是boolean onAdvance(int phase, int registeredParties)方法。
 * 此方法有2个作用：
 * 1、当每一个阶段执行完毕，此方法会被自动调用，因此，重载此方法写入的代码会在每个阶段执行完毕时执行，相当于CyclicBarrier的barrierAction。
 * 2、当此方法返回true时，意味着Phaser被终止，因此可以巧妙的设置此方法的返回值来终止所有线程。
 *
 * @author wfh
 * @create 2024/1/31 16:41
 */
public class MyPhaser extends Phaser {

    /*
        5个学生一起参加考试，一共有三道题，要求所有学生到齐才能开始考试，全部同学都做完第一题，学生才能继续做第二题，全部学生做完了第二题，才能做第三题，所有学生都做完的第三题，考试才结束。
     */

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        //在每个阶段执行完成后回调的方法
        switch (phase) {
            case 0:
                return studentArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean studentArrived() {
        System.out.println("学生准备好了,学生人数：" + getRegisteredParties());
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.println("第一题所有学生做完");
        return false;
    }

    private boolean finishSecondExercise() {
        System.out.println("第二题所有学生做完");
        return false;
    }

    private boolean finishExam() {
        System.out.println("第三题所有学生做完，结束考试");
        return true;
    }
}
