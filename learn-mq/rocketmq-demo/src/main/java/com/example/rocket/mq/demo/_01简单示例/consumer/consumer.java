package com.example.rocket.mq.demo._01简单示例.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author wfh
 * @create 2023/5/31 9:58
 */
public class consumer {

    public static void main(String[] args) throws Exception {

        //1. 创建 DefaultMQPushConsumer 对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_4");
        //2. 设置 RocketMQ Namesrv 地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //3. 设置消费进度，从 Topic 最初位置开始
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //4. 订阅 TopicTest 主题
        //设置订阅 "TopicTest" 主题的消息。有一定一定要注意！！！消费者组的消费者实例必须订阅完全相同的 Topic + Tag
        consumer.subscribe("TopicTest", "TagA");
        //5. 添加消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //6. 启动 producer 消费者
        consumer.start();
        // 打印 Consumer 启动完成
        System.out.printf("Consumer Started.%n");
    }
}
