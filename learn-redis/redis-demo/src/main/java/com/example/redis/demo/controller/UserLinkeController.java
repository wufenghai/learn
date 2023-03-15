package com.example.redis.demo.controller;

import com.example.redis.demo.model.dto.UserLikCountDTO;
import com.example.redis.demo.model.dto.UserLikesDto;
import com.example.redis.demo.service.UserLikeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wfh
 * @create 2023/3/13 9:42
 */
@Api("contrller") //swagger标签
@RestController
@RequiredArgsConstructor
public class UserLinkeController {

    @Autowired
    UserLikeService userLikeService;
    @GetMapping("/userLinke")
    public Object userLinke(){
        return userLikeService.likeStatus("1","2");
    }

    @GetMapping("/getLikedDataFromRedis")
    public List<UserLikesDto> getLikedDataFromRedis(){
        return userLikeService.getLikedDataFromRedis();
    }

    @GetMapping("/getLikedCountFromRedis")
    public List<UserLikCountDTO> getLikedCountFromRedis(){
        return userLikeService.getLikedCountFromRedis();
    }
}
