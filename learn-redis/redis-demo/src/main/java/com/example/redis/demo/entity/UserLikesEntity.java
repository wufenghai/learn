package com.example.redis.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author wfh
 * @create 2023/3/13 9:43
 */
@Data
@TableName("user_likes")
public class UserLikesEntity {

    @TableId("id")
    private String id;

    @TableField("info_id")
    private String infoId;

    @TableField("create_time")
    private Date createTime;

    @TableField("like_user_id")
    private String likeUserId;

    @TableField("update_time")
    private Date updateTime;

    @TableField("status")
    private Integer status;
}