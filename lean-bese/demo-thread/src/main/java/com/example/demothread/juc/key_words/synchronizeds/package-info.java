/*
    加锁和释放锁的原理：javap -v
        Monitorenter和Monitorexit指令，会让对象在执行，使其锁计数器加1或者减1。
        每一个对象在同一时间只与一个monitor(锁)相关联，而一个monitor在同一时间只能被一个线程获得，一个对象在尝试获得与这个对象相关联的
        Monitor锁的所有权的时候，monitorenter指令会发生如下3中情况之一：
            1.monitor计数器为0，意味着目前还没有被获得，那这个线程就会立刻获得然后把锁计数器+1，一旦+1，别的线程再想获取，就需要等待
            2.如果这个monitor已经拿到了这个锁的所有权，又重入了这把锁，那锁计数器就会累加，变成2，并且随着重入的次数，会一直累加
            3.这把锁已经被别的线程获取了，等待锁释放

        monitorexit指令：释放对于monitor的所有权，释放过程很简单，就是讲monitor的计数器减1，如果减完以后，计数器不是0，
            则代表刚才是重入进来的，当前线程还继续持有这把锁的所有权，如果计数器变成0，则代表当前线程不再拥有该monitor的所有权，即释放锁。

    可重入原理：加锁次数计数器
    保证可见性的原理：内存模型和happens-before规则




 */
package com.example.demothread.juc.key_words.synchronizeds;