package com.example.stream.rocket.mq.consumer.listener;

import com.example.stream.rocket.mq.consumer.message.Demo01Message;
import com.example.stream.rocket.mq.consumer.message.MySink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author wfh
 * @create 2023/5/31 13:57
 */
@Component
@Slf4j
public class Demo01Consumer {

    @StreamListener(MySink.DEMO01_INPUT)
    public void onMessage(@Payload Demo01Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
