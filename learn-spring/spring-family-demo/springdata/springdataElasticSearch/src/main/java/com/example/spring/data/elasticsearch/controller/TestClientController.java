package com.example.spring.data.elasticsearch.controller;

import com.example.spring.data.elasticsearch.model.Page;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wfh
 * @create 2023/4/12 10:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/testClient")
public class TestClientController {

    private final RestHighLevelClient restHighLevelClient;


    @GetMapping("/MapData")
    public Map getTestMapData(String no) throws IOException {
        // request
        SearchRequest request = new SearchRequest("order_test");
        // 查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termsQuery("no", "DD202205280003"));
        request.source(builder);
        // 执行查询
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 获取数据
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> result = hit.getSourceAsMap();
            System.out.println(result);
        }
        return null;
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     * @throws IOException
     */
    @PostMapping("/Page")
    public List<SearchHit> getTestMapData(@RequestBody Page page) throws IOException {
        // 构建DSL语句
        SearchRequest request = new SearchRequest(page.getIndex());
        // 查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //多条件查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchPhraseQuery("no", page.getFormId()));
        //条件查询
        if (page.getKeyword() != null && !"".equals(page.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("address", page.getKeyword()));
        }
        //指定使用bool查询
        builder.query(boolQuery);
        //分页
        builder.from(page.getFrom());
        builder.size(page.getSize());
        builder.sort("create_time", SortOrder.DESC);
        request.source(builder);
        // 执行查询
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 获取数据
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> result = hit.getSourceAsMap();
            System.out.println(result);
        }

        //需要进行分装数据
        return Arrays.stream(response.getHits().getHits()).collect(Collectors.toList());
    }
}
