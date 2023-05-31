package com.example.stream.rocket.mq.producer.controller;

import com.example.stream.rocket.mq.producer.message.Demo01Message;
import com.example.stream.rocket.mq.producer.message.MySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author wfh
 * @create 2023/5/31 13:48
 */
@Slf4j
@RestController
@RequestMapping("/demo01")
public class Demo01Controller {

    @Resource
    private MySource mySource;

    /**
     * 普通消息发送
     * @return
     */
    @GetMapping("/send")
    public boolean send() {
        // 1. 创建 Message
        Demo01Message message = new Demo01Message().setId(new Random().nextInt());
        // 2. 创建 spring message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message).build();
        // 3. 发送消息
        return mySource.demo01Output().send(springMessage);
    }

    /**
     * 定时消息
     * @return
     */
    @GetMapping("/send_delay")
    public boolean sendDelay() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, "3") // <X> 设置延迟级别为 3，10 秒后消费。
                .build();
        // 发送消息
        boolean sendResult = mySource.demo01Output().send(springMessage);
        log.info("[sendDelay][发送消息完成, 结果 = {}]", sendResult);
        return sendResult;
    }

}
