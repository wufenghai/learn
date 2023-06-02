package com.example.learn.framework.common.util.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Duration;
import java.util.concurrent.Executors;

/**
 * Cache 工具类
 *
 * @author wfh
 * @create 2023/6/2 10:13
 */
public class CacheUtils {
    /*
        首先guava的本地缓存可以理解成一个缓存map，以kv的形式存数据，不会持久化，没有支持分布式。
        比redis使用起来方便，不用引入额外的组件。如果是单机缓存的话，可以首先选择使用这种缓存方式。

        Guava cache的设计来源于CurrentHashMap，是线程安全的，可以按照多种策略来清理存储在其中的缓存值且保持很高的并发读写性能。
        常见应用场景：对性能有非常高的要求、不经常变化、占用内存不大、有访问整个集合的需求、数据允许不时时一致。

     */

    /**
     *
     * @param duration  缓存刷新时间
     * @param loader    刷新时间过后，需要执行的动作
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K,V>LoadingCache<K,V> buildAsyncReloadingCache(Duration duration, CacheLoader<K,V> loader){

        return CacheBuilder.newBuilder()
                // 只阻塞当前数据加载线程，其他线程返回旧值;设置时间刷新缓存
                .refreshAfterWrite(duration)
                // 通过 asyncReloading 实现全异步加载，包括 refreshAfterWrite 被阻塞的加载线程
                .build(CacheLoader.asyncReloading(loader, Executors.newCachedThreadPool()));
    }
}
