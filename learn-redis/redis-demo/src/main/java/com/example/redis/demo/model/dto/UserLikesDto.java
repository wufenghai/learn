package com.example.redis.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wfh
 * @create 2023/3/13 9:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLikesDto {
    private String infoId;
    private String likeUserId;
    private Integer status;

}