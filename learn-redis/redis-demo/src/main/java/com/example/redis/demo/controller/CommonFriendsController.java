package com.example.redis.demo.controller;

import com.example.redis.demo.entity.CommonFriends;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * redis共同好友实现
 *
 * @author wfh
 * @create 2023/3/9 19:16
 */
@Api("CommonFriendsController") //swagger标签
@Slf4j
@RestController
@RequestMapping("/commonFriends")
@RequiredArgsConstructor
public class CommonFriendsController {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 前缀
     */
    private final String PREFIX_KEY = "commonFriends:id:";

    /**
     * 添加关注
     */
    @PostMapping("/save")
    public void save() {
        CommonFriends commonFriends5 = new CommonFriends(5L, "李小龙", "男", 18);
        stringRedisTemplate.opsForSet().add(PREFIX_KEY + "wangwu", commonFriends5.getId().toString());
    }

    /**
     * 取消关注
     */
    @PostMapping("/delete")
    public void delete() {
        CommonFriends commonFriends5 = new CommonFriends(5L, "李小龙", "男", 18);
        stringRedisTemplate.opsForSet().remove(PREFIX_KEY + "lisi", commonFriends5.getId().toString());
    }


    /**
     * 取交集
     * @return
     */
    @GetMapping("/getCommonFriends")
    public Set<String> getCommonFriends() {
        Set<String> intersect = stringRedisTemplate.opsForSet().intersect(PREFIX_KEY + "lisi", PREFIX_KEY + "wangwu");
        return intersect;
    }

    /**
     * 初始化
     */
    @GetMapping("/init")
    public void init() {
        List<CommonFriends> friendsToLisi = getFriendsToLisi();
        for (CommonFriends commonFriends : friendsToLisi) {
            stringRedisTemplate.opsForSet().add(PREFIX_KEY + "lisi", commonFriends.getId().toString());
        }
        List<CommonFriends> friendsToWangwu = getFriendsToWangwu();
        for (CommonFriends commonFriends : friendsToWangwu) {
            stringRedisTemplate.opsForSet().add(PREFIX_KEY + "wangwu", commonFriends.getId().toString());
        }
    }

    /**
     * 李四的交际圈
     *
     * @return
     */
    public List<CommonFriends> getFriendsToLisi() {
        List<CommonFriends> friends = new ArrayList<>();
        CommonFriends commonFriends1 = new CommonFriends(1L, "张三", "男", 17);
        CommonFriends commonFriends2 = new CommonFriends(2L, "王五", "男", 18);
        CommonFriends commonFriends3 = new CommonFriends(3L, "孙悟空", "男", 18);
        CommonFriends commonFriends4 = new CommonFriends(4L, "猪八戒", "男", 18);
        CommonFriends commonFriends5 = new CommonFriends(5L, "李小龙", "男", 18);
        friends.add(commonFriends1);
        friends.add(commonFriends2);
        friends.add(commonFriends3);
        friends.add(commonFriends4);
        friends.add(commonFriends5);
        return friends;
    }

    /**
     * 王五的交际圈
     *
     * @return
     */
    public List<CommonFriends> getFriendsToWangwu() {
        List<CommonFriends> friends = new ArrayList<>();
        CommonFriends commonFriends1 = new CommonFriends(1L, "张三", "男", 17);
        CommonFriends commonFriends2 = new CommonFriends(6L, "李四", "男", 18);
        CommonFriends commonFriends3 = new CommonFriends(3L, "孙悟空", "男", 18);
        friends.add(commonFriends1);
        friends.add(commonFriends2);
        friends.add(commonFriends3);
        return friends;
    }
}
