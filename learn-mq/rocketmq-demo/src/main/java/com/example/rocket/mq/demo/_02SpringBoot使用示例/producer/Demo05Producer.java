package com.example.rocket.mq.demo._02SpringBoot使用示例.producer;

import com.example.rocket.mq.demo._02SpringBoot使用示例.message.Demo05Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  广播消费
 *  在上述的示例中，我们看到的都是使用集群消费。而在一些场景下，我们需要使用广播消费。
 *  广播消费模式下，相同 Consumer Group 的每个 Consumer 实例都接收全量的消息。
 * @author wfh
 * @create 2023/5/31 11:39
 */
@Component
public class Demo05Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id) {
        // 创建 Demo05Message 消息
        Demo05Message message = new Demo05Message();
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSend(Demo05Message.TOPIC, message);
    }

}