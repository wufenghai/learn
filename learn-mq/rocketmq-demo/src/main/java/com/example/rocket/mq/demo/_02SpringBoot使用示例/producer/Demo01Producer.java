package com.example.rocket.mq.demo._02SpringBoot使用示例.producer;

import com.example.rocket.mq.demo._02SpringBoot使用示例.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wfh
 * @create 2023/5/31 10:47
 */
@Component
public class Demo01Producer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id){
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //同步发送消息
        return rocketMQTemplate.syncSend(Demo01Message.TOPIC,message);
    }

    public void asyncSend(Integer id, SendCallback callback) {
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 异步发送消息
        rocketMQTemplate.asyncSend(Demo01Message.TOPIC, message, callback);
    }

    public void onewaySend(Integer id) {
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // oneway 发送消息
        rocketMQTemplate.sendOneWay(Demo01Message.TOPIC, message);
    }



}
