package com.example.redis.demo.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * redis 计数器实现
 *
 * @author wfh
 * @create 2023/3/9 16:46
 */
@Api("CounterController") //swagger标签
@Slf4j
@RestController
@RequestMapping("/counter")
@RequiredArgsConstructor
public class CounterController {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 前缀
     */
    private final String PREFIX_KEY = "counter:ip:";

    @PostMapping("/hello/{ip}")
    public String count(@PathVariable("ip") String ip) {
        /**
         * 判断是否达到次数
         */
        Boolean aBoolean = invokeExceededTimes(PREFIX_KEY+ip, 1, 3);
        if (aBoolean) {
            log.info("可以访问");
            return "可以访问";
        } else {
            log.info("请求次数达标了");
            return "请求次数达标了";
        }
    }

    /**
     * 判断同一个key在规定时间内访问次数是否到达了最高值
     *
     * @param key   键
     * @param days  时间
     * @param count 一定时间内的访问次数
     * @return
     */
    public Boolean invokeExceededTimes(String key, int days, int count) {
        log.info("invokeExceededTimes key:{},days:{},count:{}", key, days, count);
        // 判断在redis 中是否由key值
        Boolean redisKey = stringRedisTemplate.hasKey(key);
        if (redisKey) {
            //获取key所对应的value
            Integer hasKey = Integer.valueOf(stringRedisTemplate.opsForValue().get(key));
            if (hasKey > count) {
                return false;
            }
            // 对value进行加1操作
            stringRedisTemplate.opsForValue().increment(key, 1);
            return true;
        } else {
            //如果没有key值 ，对他进行添加到redis中
            stringRedisTemplate.opsForValue().set(key, "1", days, TimeUnit.DAYS);
        }
        return true;
    }
}
