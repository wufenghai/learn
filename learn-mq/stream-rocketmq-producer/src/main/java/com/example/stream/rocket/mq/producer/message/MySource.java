package com.example.stream.rocket.mq.producer.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author wfh
 * @create 2023/5/31 13:43
 */
public interface MySource {

    @Output("demo01-output")
    MessageChannel demo01Output();

}