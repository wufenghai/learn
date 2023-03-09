package com.example.redis.demo.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * redis 限速器
 *
 * @author wfh
 * @create 2023/3/9 17:33
 */
@Api("CounterController") //swagger标签
@Slf4j
@RestController
@RequestMapping("/limiter")
@RequiredArgsConstructor
public class LimiterController {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 前缀
     */
    private final String PREFIX_KEY = "sms:phone:";

    /**
     * 验证码验证
     *
     * @param phone
     * @param uuid
     * @return
     */
    @PostMapping("/verification/{phone}/{uuid}")
    public String verification(@PathVariable("phone") String phone, @PathVariable("uuid") String uuid) {
        return verifyKey(PREFIX_KEY + phone, uuid);
    }

    /**
     * 通过电话获取验证码，有效时限1分钟
     *
     * @param phone
     * @return
     */
    @PostMapping("/verification/{phone}")
    public String getVerification(@PathVariable("phone") String phone) {
        phone = PREFIX_KEY + phone;
        Boolean aBoolean = stringRedisTemplate.hasKey(phone);
        if (aBoolean) {
            return stringRedisTemplate.opsForValue().get(phone);
        }
        //验证码
        String id = String.valueOf((int) (Math.random() * 10000));
        stringRedisTemplate.opsForValue().set(phone, id, 1, TimeUnit.MINUTES);
        return id;
    }


    /**
     * 判断同验证码是否有效
     *
     * @param key 键
     * @param id  验证码
     * @return
     */
    public String verifyKey(String key, String id) {
        log.info("verifyKey key:{} uuid:{}", key, id);
        //判断是否存在该值
        Boolean aBoolean = stringRedisTemplate.hasKey(key);
        if (aBoolean) {
            String temp = stringRedisTemplate.opsForValue().get(key);
            if (temp.equals(id)) {
                return "验证成功";
            }
            return "验证失败，请确认验证码信息";
        } else {
            return "验证码已过期";
        }
    }
}
