/*
    1 Happens-Before（先行发生）原则的定义
    对应链接：https://blog.csdn.net/zhuanzhuantech/article/details/125990000?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170659630516800225559287%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170659630516800225559287&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_click~default-2-125990000-null-null.142^v99^pc_search_result_base3&utm_term=happens-before&spm=1018.2226.3001.4187
        程序次序规则（Program Order Rule）：在一个线程内，按照控制流顺序，书写在前面的操作先行发生于书写在后面的操作。
            在一个线程内，按照控制流顺序，如果操作A先行发生于操作B，那么操作A所产生的影响对于操作B是可见的。

        管程锁定规则（Monitor Lock Rule）：一个unlock操作先行发生于后面对同一个锁的lock操作。
            对于同一个锁，如果一个unlock操作先行发生于一个lock操作，那么该unlock操作所产生的影响对于该lock操作是可见的。

        volatile变量规则（Volatile Variable Rule）：对一个volatile变量的写操作先行发生于后面对这个变量的读操作。
            对于同一个volatile变量，如果对于这个变量的写操作先行发生于这个变量的读操作，那么对于这个变量的写操作所产的影响对于这个变量的读操作是可见的。

        线程启动规则（Thread Start Rule）：Thread对象start()方法先行发生于此线程的每一个动作。
            对于同一个Thread对象，该Thread对象的start()方法先行发生于此线程的每一个动作，也就是说对线程start()方法调用所产生的影响对于该该线程的每一个动作都是可见的。

        线程终止规则（Thread Termination Rule）：线程中的所有操作都先行发生于对此线程的终止检测，我们可以通过Thread.join()方法和Thread.isAlive()的返回值等手段检测线程是否已经终止执行。
            对于一个线程，线程中发生的所有操作先行发生于对此线程的终止检测，也就是说线程中的所有操作所产生的影响对于调用线程Thread.join()方法或者Thread.isAlive()方法都是可见的。

        线程中断规则（Thread Interruption Rule）：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生，可以通过Thread.interrupted()方法检测到是否有中断发生。
            对于同一个线程，对线程interrupt()方法的调用先行发生于该线程检测到中断事件的发生，也就是说线程interrupt()方法调用所产生的影响对于该线程检测到中断事件是可见的。

        对象终结规则（Finalizer Rule） ：一个对象的初始化完成（构造函数结束）先行发生于它的finalize()方法的开始。
            对于同一个对象，它的构造方法执行结束先行发生于它的finalize()方法的开始，也就是说一个对象的构造方法结束所产生的影响，对于它的finalize()方法开始执行是可见的。

        传递性（Transitivity）：如果操作A先行发生于操作B，操作B先行发生于操作C，那就可以得出操作A先行发生于操作C的结论。
            如果操作A先行发生于操作B，操作B先行发生于操作C，则操作A先行发生于操作C，也就说操作A所产生的所有影响对于操作C是可见的。






 */
package com.example.demothread.juc.key_words.happens_before;