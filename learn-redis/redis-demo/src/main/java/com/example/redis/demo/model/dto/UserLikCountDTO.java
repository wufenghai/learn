package com.example.redis.demo.model.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @author wfh
 * @create 2023/3/13 9:37
 */
@Data
public class UserLikCountDTO implements Serializable {

    private String infoId;
    private Integer value;

    public UserLikCountDTO(String infoId, Integer value) {
        this.infoId = infoId;
        this.value = value;
    }
}
