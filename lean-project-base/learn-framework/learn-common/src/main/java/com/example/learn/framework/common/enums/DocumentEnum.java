package com.example.learn.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文档地址
 */
@Getter
@AllArgsConstructor
public enum DocumentEnum {

    REDIS_INSTALL("redis", "redis 安装文档"),
    TENANT("ten", "SaaS 多租户文档");

    /**
     * 文档路径
     */
    private final String url;
    /**
     * 文档描述
     */
    private final String memo;

}
