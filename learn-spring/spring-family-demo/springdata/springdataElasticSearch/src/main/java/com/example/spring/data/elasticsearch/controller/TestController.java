package com.example.spring.data.elasticsearch.controller;

import com.example.spring.data.elasticsearch.model.Order;
import com.example.spring.data.elasticsearch.model.PageResult;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.RescorerQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wfh
 * @create 2023/4/11 17:43
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final ElasticsearchRestTemplate restTemplate;

    /**
     * 精确查询 Term
     *
     * GET order_test/_search
     * {
     *   "query": {
     *     "term": {
     *       "no": {
     *         "value": "DD202205280003"
     *       }
     *     }
     *   }
     * }
     * @param no
     * @return
     */
    @GetMapping("/getByNo")
    public Order getByNo(String no){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termsQuery("no",no));
        SearchHit<Order> searchRes = restTemplate.searchOne(queryBuilder.build(), Order.class);
        return searchRes == null ? null : searchRes.getContent();
    }


    /**
     *多值查询 Terms
     *
     * GET order_test/_search
     * {
     *   "query": {
     *     "terms": {
     *       "status": [
     *         0,
     *         1
     *       ]
     *     }
     *   }
     * }
     * @param page
     * @param size
     * @param status
     * @return
     */
    @GetMapping("/pageByStatus")
    public PageResult<Order> pageByStatus(int page, int size, int ...status){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termsQuery("status",status)).withPageable(PageRequest.of(page, size));
        SearchHits<Order> search = restTemplate.search(queryBuilder.build(), Order.class);
        List<Order> collect = search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
        return PageResult.data(collect,search.getTotalHits());
    }


    /**
     * 范围查询 Range
     *
     * GET order_test/_search
     * {
     *   "query": {
     *     "range": {
     *       "amount": {
     *         "gt": 100
     *       }
     *     }
     *   }
     * }
     *
     * @param page
     * @param size
     * @param amount
     * @return
     */
    @GetMapping("/listGreaterThanAmount")
    public List<Order> listGreaterThanAmount(int page,int size,Double amount){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.rangeQuery("amount").gt(amount)).withPageable(PageRequest.of(page, size));
        SearchHits<Order> search = restTemplate.search(queryBuilder.build(), Order.class);
        return search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


    /**
     * 模糊查询 Match
     *
     * GET order_test/_search
     * {
     *   "query": {
     *     "match": {
     *       "address": "北京"
     *     }
     *   }
     * }
     * @param page
     * @param size
     * @param address
     * @return
     */
    @GetMapping("listMatchAddress")
    public List<Order> listMatchAddress(int page,int size,String address){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("address",address)).withPageable(PageRequest.of(page, size));
        SearchHits<Order> search = restTemplate.search(queryBuilder.build(), Order.class);
        return search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


    /**
     * 嵌套查询 Boolean
     *
     * GET order_test/_search
     * {
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "term": {
     *             "status": {
     *               "value": 3
     *             }
     *           }
     *         },
     *         {
     *           "range": {
     *             "create_time": {
     *               "gte": "2022-05-01 00:00:00",
     *               "lt": "2022-06-01 00:00:00"
     *             }
     *           }
     *         }
     *       ]
     *     }
     *   }
     * }
     *
     * @param page
     * @param size
     * @param startTime
     * @param endTime
     * @param status
     * @return
     */
    @GetMapping("/listRangeTimeAndStatus")
    public List<Order> listRangeTimeAndStatus(int page, int size, Date startTime, Date endTime, Integer ...status){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termsQuery("status",status))
                .must(QueryBuilders.rangeQuery("create_time").gte(startTime).lt(endTime));
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(boolQueryBuilder).withPageable(PageRequest.of(page, size));
        SearchHits<Order> search = restTemplate.search(queryBuilder.build(), Order.class);
        return search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


}
