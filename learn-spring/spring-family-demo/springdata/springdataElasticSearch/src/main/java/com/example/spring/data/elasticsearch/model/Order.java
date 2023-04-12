package com.example.spring.data.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * @author wfh
 * @create 2023/4/11 17:37
 */
@Data
@Document(indexName = "order_test")
@Setting(replicas = 0)
public class Order {

    @Id
    private String id;

    // 订单状态 0未付款 1未发货 2运输中 3待签收 4已签收
    @Field(type = FieldType.Integer, name = "status")
    private Integer status;

    @Field(type = FieldType.Keyword, name = "no")
    private String no;

    @Field(type = FieldType.Date, name = "create_time", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Field(type = FieldType.Double, name = "amount")
    private Double amount;

    @Field(type = FieldType.Keyword, name = "creator")
    private String creator;

    @GeoPointField
    @Field(name = "point")
    private GeoPoint point;

    @Field(type = FieldType.Text, name = "address", analyzer = "ik_max_word")
    private String address;

    @Field(type = FieldType.Nested, name = "creator")
    private List<Product> product;

}


