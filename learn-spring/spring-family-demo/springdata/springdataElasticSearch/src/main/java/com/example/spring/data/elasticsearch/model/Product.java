package com.example.spring.data.elasticsearch.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author wfh
 * @create 2023/4/11 17:38
 */
@Data
public class Product implements Serializable {

    @Field(type = FieldType.Long, name = "id")
    private Long id;

    @Field(type = FieldType.Keyword, name = "name")
    private String name;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

    @Field(type = FieldType.Integer, name = "quantity")
    private Double quantity;

}