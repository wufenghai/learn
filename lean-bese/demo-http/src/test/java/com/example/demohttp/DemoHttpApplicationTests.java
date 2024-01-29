package com.example.demohttp;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.example.demohttp.util.HttpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class DemoHttpApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void getTest() {
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
//        String result1 = HttpUtils.get("http://localhost:18888/http/get-test");
//        System.out.println(result1);

//        // 当无法识别页面编码的时候，可以自定义请求页面的编码
//        String result2 = HttpUtils.get("https://www.baidu.com", CharsetUtil.CHARSET_UTF_8);
//
//        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("id", "1");
//
//        String result3 = HttpUtils.get("http://localhost:18888/http/get-test", paramMap);
//        System.out.println(result3);

        List<Map> query = new ArrayList<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", "id");
        paramMap.put("value", "456");
        query.add(paramMap);

        HttpResponse response = HttpUtils.sendGet("http://127.0.0.1:18888/http/get-test", null, query);
        System.out.println(response.body());
    }

    @Test
    public void postTest1() {
        List<Map> query = new ArrayList<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", "name");
        paramMap.put("value", "wfh");
        query.add(paramMap);
        HashMap<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("key", "age");
        paramMap2.put("value", "12");
        query.add(paramMap2);

        HttpResponse response = HttpUtils.sendPost("http://127.0.0.1:18888/http/post-test1", null, null, null, query);
        System.out.println(response.body());
    }

    @Test
    public void postTest2() {

        List<Map> query = new ArrayList<>();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", "id");
        paramMap.put("value", "2");
        query.add(paramMap);

        List<Map> body = new ArrayList<>();
        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("key", "name");
        bodyMap.put("value", "wfh");
        body.add(bodyMap);
        HashMap<String, Object> bodyMap2 = new HashMap<>();
        bodyMap2.put("key", "age");
        bodyMap2.put("value", "12");
        body.add(bodyMap2);


        HttpResponse response = HttpUtils.sendPost("http://127.0.0.1:18888/http/post-test2?id=2", null, null, null, body);
        System.out.println(response.body());
    }
}
