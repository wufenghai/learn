package com.example.rocket.mq.demo._02SpringBoot使用示例.message;

import lombok.Data;

/**
 * @author wfh
 * @create 2023/5/31 10:44
 */
@Data
public class Demo02Message {

    public static final String TOPIC = "DEMO_02";

    /**
     * 编号
     */
    private Integer id;
}
