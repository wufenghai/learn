package com.example.stream.rocket.mq.producer;

import com.example.stream.rocket.mq.producer.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MySource.class)
public class StreamRocketmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamRocketmqProducerApplication.class, args);
    }

}
