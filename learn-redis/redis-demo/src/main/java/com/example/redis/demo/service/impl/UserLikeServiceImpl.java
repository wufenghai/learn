package com.example.redis.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.redis.demo.entity.UserLikesEntity;
import com.example.redis.demo.mapper.UserLikesDao;
import com.example.redis.demo.model.dto.UserLikCountDTO;
import com.example.redis.demo.model.dto.UserLikesDto;
import com.example.redis.demo.model.enums.LikedStatusEum;
import com.example.redis.demo.service.UserLikeService;
import com.example.redis.demo.utils.RedisKeyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wfh
 * @create 2023/3/13 9:41
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserLikeServiceImpl implements UserLikeService {

    private final RedisTemplate redisTemplate;
    private final UserLikesDao userLikesDao;

    public Object likeStatus(String infoId, String likeUserId) {
        if (redisTemplate.opsForHash().hasKey(RedisKeyUtils.MAP_KEY_USER_LIKED, RedisKeyUtils.getLikedKey(infoId, likeUserId))) {
            String o = redisTemplate.opsForHash().get(RedisKeyUtils.MAP_KEY_USER_LIKED, RedisKeyUtils.getLikedKey(infoId, likeUserId)).toString();
            if ("1".equals(o)) {
                unLikes(infoId, likeUserId);
                return LikedStatusEum.UNLIKE;
            }
            if ("0".equals(o)) {
                likes(infoId, likeUserId);
                return LikedStatusEum.LIKE;
            }
        }
        UserLikesEntity userLikes = userLikesDao.selectOne(Wrappers.<UserLikesEntity>lambdaQuery().eq(UserLikesEntity::getInfoId, infoId).eq(UserLikesEntity::getLikeUserId, likeUserId));
        if (userLikes == null) {
            UserLikesEntity userLikes1 = new UserLikesEntity();
            userLikes1.setInfoId(infoId);
            userLikes1.setLikeUserId(likeUserId);
            userLikesDao.insert(userLikes1);
            likes(infoId, likeUserId);
            return LikedStatusEum.LIKE;
        }
        if (userLikes.getStatus() == 1) {
            unLikes(infoId, likeUserId);
            return LikedStatusEum.UNLIKE;
        }

        if (userLikes.getStatus() == 0) {
            likes(infoId, likeUserId);
            return LikedStatusEum.LIKE;
        }
        return "";
    }

    public void likes(String infoId, String likeUserId) {
        String likedKey = RedisKeyUtils.getLikedKey(infoId, likeUserId);
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, infoId, 1);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, likedKey, LikedStatusEum.LIKE.getStatus());
    }

    public void unLikes(String infoId, String likeUserId) {
        String likedKey = RedisKeyUtils.getLikedKey(infoId, likeUserId);
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, infoId, -1);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, likedKey);
    }


    public List<UserLikesDto> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLikesDto> list = new ArrayList<>();
        while (scan.hasNext()) {
            Map.Entry<Object, Object> entry = scan.next();
            String key = (String) entry.getKey();
            String[] split = key.split("::");
            String infoId = split[0];
            String likeUserId = split[1];
            Integer value = (Integer) entry.getValue();
            //组装成 UserLike 对象
            UserLikesDto userLikeDetail = new UserLikesDto(infoId, likeUserId, value);
            list.add(userLikeDetail);
            //存到 list 后从 Redis 中删除
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }
        return list;
    }

    public List<UserLikCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<UserLikCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            String key = (String) map.getKey();
            Integer value = (Integer) map.getValue();
            UserLikCountDTO userLikCountDTO = new UserLikCountDTO(key, value);
            list.add(userLikCountDTO);
//            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }


}