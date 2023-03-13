package com.example.redis.demo.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author wfh
 * @create 2023/3/13 9:44
 */
@Data
@TableName("video")
public class VideoEntity {

    @TableId("likes_number")
    private String id;

    /**
     * 点赞数
     */
    @TableField("likes_number")
    private Integer likesNumber;

    /**
     * 评论数
     */
    @TableField("comments_number")
    private Integer commentsNumber;

    /**
     * 分享数
     */
    @TableField("share_number")
    private Integer shareNumber;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user")
    private String createUser;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_user")
    private String updateUser;
}