package com.example.rocket.mq.demo._01简单示例.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author wfh
 * @create 2023/5/31 9:45
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //1.1 创建 defaultMQProducer 对象
        //创建 DefaultMQProducer 对象，这里设置的生产者分组是 "please_rename_unique_group_name" 。
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        //1.2 设置 RocketMQ Namesrv 地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //1.3 启动 producer 生产者
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                //2.1 创建 message 消息
                //创建 Message 消息。这里设置了其 Topic 为 "TopicTest"，Tag 为 TagA、消息体 Body 为 "Hello RocketMQ" 的二进制数组。
                Message message = new Message("TopicTest", "TagA", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                //2.2 同步发送消息
                //调用生产者的 #send(Message msg) 方法，同步发送消息，等待发送结果。RocketMQ Producer 一共有三种发送消息的方式，
                // 除了我们这里看到的同步发送消息之外，还有异步发送消息(可见 AsyncProducer 示例)，和 Oneway 发送消息。
                SendResult result = producer.send(message);
                //2.3 打印发送消息
                System.out.printf("%s%n", result);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }

        }

        //3. 关闭 producer 生产者
        producer.shutdown();
    }
}
