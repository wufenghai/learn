package com.example.demothread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    众所周知，CPU、内存、I/O 设备的速度是有极大差异的，为了合理利用 CPU 的高性能，
    平衡这三者的速度差异，计算机体系结构、操作系统、编译程序都做出了贡献，主要体现为:
        1.CPU 增加了缓存，以均衡与内存的速度差异；// 导致 可见性问题
        2.操作系统增加了进程、线程，以分时复用 CPU，进而均衡 CPU 与 I/O 设备的速度差异；// 导致 原子性问题
        3.编译程序优化指令执行次序，使得缓存能够得到更加合理地利用。// 导致 有序性问题# 线程不安全示例

 */
@SpringBootApplication
public class DemoThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoThreadApplication.class, args);
    }

}
