package com.example.spring.data.elasticsearch.model;

/**
 * @author wfh
 * @create 2023/4/12 9:28
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    private List<T> data;

    private long total;

    public static <T> PageResult<T> data(List<T> data, long count) {
        return new PageResult<>(data, count);
    }

}
