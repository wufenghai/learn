package com.example.demothread.juc.util.thread_local;

/**
 * 每个线程维护了一个“序列号”
 *
 * @author wfh
 * @create 2024/1/31 18:14
 */
public class SerialNum {

    private static int nextSerialNum = 0;

    private static ThreadLocal<Integer> serialNum = ThreadLocal.withInitial(() ->
            new Integer(nextSerialNum++)
    );

    public static int get() {
        return ((Integer) (serialNum.get())).intValue();
    }



}
