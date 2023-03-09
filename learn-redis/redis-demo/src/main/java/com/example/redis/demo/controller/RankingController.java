package com.example.redis.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * redis 排行榜实现
 * 用户分数排行榜，分数相同的情况下，获得此分数时间最早的用户排前面
 *
 * @author wfh
 * @create 2023/3/8 9:17
 */
@Api("RankingController") //swagger标签
@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RedisTemplate redisTemplate;

    //排行榜在redis中的key
    //可以改造加上月份，实现每个月的排行榜单独存在，不用清楚历史数据
    private final String rankingKey = "ranking";

    /**
     * 给用户加一分
     * @param name
     * @return
     */
    @ApiOperation(value = "addCore接口")//swagger标签
    @GetMapping("addCore")
    public String addCore(@RequestParam("name") String name) throws ParseException {
        Double score = redisTemplate.opsForZSet().score(rankingKey, name);
        Date now = new Date();
        //当前时间的时间戳
        long mi = now.getTime();
        //本月初的时间戳
        long startMi = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date startDate = format.parse(format.format(now));
        startMi = startDate.getTime();
        System.out.println(mi - startMi);
        double dmi = 1D - (double) (mi - startMi) / 10000000000D;
        //保留10位小数，舍去10位以后的数值
        BigDecimal b = new BigDecimal(dmi);
        dmi = b.setScale(10, BigDecimal.ROUND_DOWN).doubleValue();

        System.out.println(dmi);
        double newScore;
        if (score == null) {
            newScore = 1D + dmi;
            System.out.println(name + "还没有分数");
        } else {
            System.out.println(name + "的分数增加前为：" + score);
            newScore = Math.floor(score) + 1D + dmi;
        }
        redisTemplate.opsForZSet().add(rankingKey, name, newScore);
        return "添加成功";
    }

    /**
     * 获取排行榜前50名的姓名和分数
     *
     * @return
     */
    @ApiOperation(value = "rankingList接口")//swagger标签
    @GetMapping("rankingList")
    public Object rankingList() {
        Set<String> rankList = redisTemplate.opsForZSet().reverseRange(rankingKey, 0L, 49L);
        List<Map> result = new ArrayList<>();
        for (String name : rankList) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            Double score = redisTemplate.opsForZSet().score(rankingKey, name);
            map.put("score", score);
            result.add(map);
        }
        return result;
    }
}
