package com.example.stream.rocket.mq.consumer;

import com.example.stream.rocket.mq.consumer.message.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MySink.class)
public class StreamRocketmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamRocketmqConsumerApplication.class, args);
    }

}
