package com.example.stream.rocket.mq.producer.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wfh
 * @create 2023/5/31 13:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Demo01Message {

    /**
     * 编号
     */
    private Integer id;
}
