package com.example.spring.data.elasticsearch.model;

import lombok.Data;

/**
 * @author wfh
 * @create 2023/4/12 11:09
 */
@Data
public class Page {

    /**
     * from后面的值有一个公式。从第几页开始：(页数-1)*每页几条数据
     */
    private Integer from;
    /**
     * size是每页条数
     */
    private Integer size;
    /**
     * es的索引名
     */
    private String index;
    /**
     * 表单id
     */
    private String formId;

    /**
     * 关键词
     */
    private String keyword;
}
