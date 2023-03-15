package com.example.redis.demo.service;

import com.example.redis.demo.model.dto.UserLikCountDTO;
import com.example.redis.demo.model.dto.UserLikesDto;

import java.util.List;

/**
 * @author wfh
 * @create 2023/3/13 9:41
 */
public interface UserLikeService {
    Object likeStatus(String infoId, String likeUserId);

    List<UserLikesDto> getLikedDataFromRedis();

    List<UserLikCountDTO> getLikedCountFromRedis();
}
