package com.example.rocket.mq.demo._02SpringBoot使用示例.producer;

import com.example.rocket.mq.demo._02SpringBoot使用示例.message.Demo04Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消费重试
 * RocketMQ 提供消费重试的机制。在消息消费失败的时候，RocketMQ 会通过消费重试机制，重新投递该消息给 Consumer ，让 Consumer 有机会重新消费消息，实现消费成功。
 * 当然，RocketMQ 并不会无限重新投递消息给 Consumer 重新消费，而是在默认情况下，达到 16 次重试次数时，Consumer 还是消费失败时，该消息就会进入到死信队列。
 *
 * @author wfh
 * @create 2023/5/31 11:35
 */
@Component
public class Demo04Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id) {
        // 创建 Demo04Message 消息
        Demo04Message message = new Demo04Message();
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSend(Demo04Message.TOPIC, message);
    }
}
