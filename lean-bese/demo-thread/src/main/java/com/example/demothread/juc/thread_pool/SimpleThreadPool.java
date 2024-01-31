package com.example.demothread.juc.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wfh
 * @create 2024/1/31 14:05
 */
public class SimpleThreadPool {

    /*
        1、newSingleThreadExecutor

        初始化的线程池中只有一个线程，如果该线程异常结束，会重新创建一个新的线程继续执行任务，唯一的线程可以保证所提交任务的顺序执行.
        由于使用了无界队列, 所以SingleThreadPool永远不会拒绝, 即饱和策略失效

        2、newFixedThreadPool

        线程池的线程数量达corePoolSize后，即使线程池没有可执行任务时，也不会释放线程。
        FixedThreadPool的工作队列为无界队列LinkedBlockingQueue(队列容量为Integer.MAX_VALUE), 这会导致以下问题:
            线程池里的线程数量不超过corePoolSize,这导致了maximumPoolSize和keepAliveTime将会是个无用参数
            由于使用了无界队列, 所以FixedThreadPool永远不会拒绝, 即饱和策略失效

        3、newScheduledThreadPool

        创建一个可定期或者延时执行任务的定长线程池，支持定时及周期性任务执行。

        4、newCachedThreadPool

         线程池的线程数可达到Integer.MAX_VALUE，即2147483647，内部使用SynchronousQueue作为阻塞队列；
        和newFixedThreadPool创建的线程池不同，newCachedThreadPool在没有任务执行时，当线程的空闲时间超过keepAliveTime，会自动释放线程资源，
        当提交新任务时，如果没有空闲线程，则创建新线程执行任务，会导致一定的系统开销；
        执行过程与前两种稍微不同:
            主线程调用SynchronousQueue的offer()方法放入task, 倘若此时线程池中有空闲的线程尝试读取 SynchronousQueue的task, 即调用了SynchronousQueue的poll(), 那么主线程将该task交给空闲线程. 否则执行(2)
            当线程池为空或者没有空闲的线程, 则创建新的线程执行任务.
            执行完任务的线程倘若在60s内仍空闲, 则会被终止. 因此长时间空闲的CachedThreadPool不会持有任何线程资源

     */
    public static void main(String[] args) {
        /*
            程序中我们创建了固定大小为五个工作线程的线程池。然后分配给线程池十个工作，因为线程池大小为五，它将启动五个工作线程先处理五个工作，
            其他的工作则处于等待状态，一旦有工作完成，空闲下来工作线程就会捡取等待队列里的其他工作进行执行。

         */
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread(" " + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        /*
            输出表明线程池中至始至终只有五个名为 "pool-1-thread-1" 到 "pool-1-thread-5" 的五个线程，
            这五个线程不随着工作的完成而消亡，会一直存在，并负责执行分配给线程池的任务，直到线程池消亡。
         */
        System.out.println("Finished all threads");
    }
}
