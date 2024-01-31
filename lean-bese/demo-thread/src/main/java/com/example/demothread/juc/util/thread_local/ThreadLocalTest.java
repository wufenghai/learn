package com.example.demothread.juc.util.thread_local;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ThreadLocal是通过线程隔离的方式防止任务在共享资源上产生冲突, 线程本地存储是一种自动化机制，可以为使用相同变量的每个不同线程都创建不同的存储。
 * ThreadLocal是一个将在多线程中为每一个线程创建单独的变量副本的类;
 * 当使用ThreadLocal来维护变量时, ThreadLocal会为每个线程创建单独的变量副本, 避免因多线程操作共享变量而导致的数据不一致的情况。
 *
 * @author wfh
 * @create 2024/1/31 17:45
 */
public class ThreadLocalTest {
}


/*
    如下数据库管理类在单线程使用是没有任何问题的
    很显然，在多线程中使用会存在线程安全问题：
        第一，这里面的2个方法都没有进行同步，很可能在openConnection方法中会多次创建connect；
        第二，由于connect是共享变量，那么必然在调用connect的地方需要使用到同步来保障线程安全，
            因为很可能一个线程在使用connect进行数据库操作，而另外一个线程调用closeConnection关闭链接。

    为了解决上述线程安全的问题，第一考虑：互斥同步
        将这段代码的两个方法进行同步处理，并且在调用connect的地方需要进行同步处理，比如用Synchronized或者ReentrantLock互斥锁。

    这地方到底需不需要将connect变量进行共享?
         事实上，是不需要的。
         假如每个线程中都有一个connect变量，各个线程之间对connect变量的访问实际上是没有依赖关系的，
         即一个线程不需要关心其他线程是否对这个connect进行了修改的。=》请看ConnectionManager2

 */
class ConnectionManager {
    private static Connection connect = null;

    public static Connection openConnection() throws SQLException {
        if (connect == null) {
            connect = DriverManager.getConnection("xxx");
        }
        return connect;
    }

    public static void closeConnection() throws SQLException {
        if (connect != null)
            connect.close();
    }
}

/*
    这样处理确实也没有任何问题，由于每次都是在方法内部创建的连接，那么线程之间自然不存在线程安全问题。
    但是这样会有一个致命的影响：导致服务器压力非常大，并且严重影响程序执行性能。
    由于在方法中需要频繁地开启和关闭数据库连接，这样不仅严重影响程序执行效率，还可能导致服务器压力巨大。
    这时候ThreadLocal登场了 =》请看ConnectionManager3
 */
class ConnectionManager2 {

    private Connection connect = null;

    public Connection openConnection() throws SQLException {
        if (connect == null) {
            connect = DriverManager.getConnection("xxx");
        }
        return connect;
    }

    public void closeConnection() throws SQLException {
        if (connect != null)
            connect.close();
    }

}

class Dao {
    public void insert() throws SQLException {
        ConnectionManager2 connectionManager2 = new ConnectionManager2();
        Connection connection = connectionManager2.openConnection();

        // 使用connection进行操作
        connectionManager2.closeConnection();
    }
}

/*
    那么这种情况下使用ThreadLocal是再适合不过的了，因为ThreadLocal在每个线程中对该变量会创建一个副本，
    即每个线程内部都会有一个该变量，且在线程内部任何地方都可以使用，线程之间互不影响，
    这样一来就不存在线程安全问题，也不会严重影响程序执行性能。

 */
class ConnectionManager3 {

    private static ThreadLocal<Connection> dbConnectionLocal = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            try {
                return DriverManager.getConnection("xxx", "xxx", "xxx");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public Connection getConnection() {
        return dbConnectionLocal.get();
    }
}

