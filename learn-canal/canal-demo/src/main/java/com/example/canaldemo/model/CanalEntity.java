package com.example.canaldemo.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wfh
 * @create 2023/4/13 11:35
 */
@Data
@Component
@ConfigurationProperties(prefix = "canal")
public class CanalEntity {
    /**
     * 主机名
     */
    @Value("${host:localhost}")
    private String host;
    /**
     * 端口号
     */
    @Value("${port:11111}")
    private Integer port;
    /**
     * 目的数据
     */
    @Value("${destination:example}")
    private String destination;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否启动时实例同步到es
     */
    @Value("${hasSyncInstanceToEs:false}")
    private boolean hasSyncInstanceToEs;
}
