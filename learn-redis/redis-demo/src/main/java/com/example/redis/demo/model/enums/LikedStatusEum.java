package com.example.redis.demo.model.enums;

/**
 * @author wfh
 * @create 2023/3/10 9:49
 */

public enum LikedStatusEum {
    LIKE(1,"点赞"),
    UNLIKE(0,"取消点赞");

    private final Integer status;
    private final String statusName;

    LikedStatusEum(Integer status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

    public static void main(String[] args) {
        System.out.println(0==0.0);
    }
}