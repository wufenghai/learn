package com.example.demohttp.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wfh
 * @create 2024/1/23 11:51
 */
public class HttpUtils extends HttpUtil {


    /**
     * 自定义get 请求
     *
     * @param url          URL字符串
     * @param headers      请求头
     * @param queryStrings 请求参数--->Form Data类型参数
     */
    public static HttpResponse sendGet(String url, List headers, List queryStrings) {
        // 创建HttpRequest对象
        HttpRequest httpRequest = getHttpRequest(url, Method.GET, headers, queryStrings, null, null);
        // 执行请求，得到http响应类
        return httpRequest.execute();
    }

    /**
     * 自定义get 请求
     *
     * @param url          URL字符串
     * @param headers      请求头
     * @param queryStrings 请求参数--->Form Data类型参数
     * @param bodyType     请求体类型（0 JSON对象 1 JSON数组）
     * @param bodyConfigs  请求体
     */
    public static HttpResponse sendPost(String url, List headers, List queryStrings,
                                        Integer bodyType, List bodyConfigs) {
        // 创建HttpRequest对象
        HttpRequest httpRequest = getHttpRequest(url, Method.POST, headers, queryStrings, bodyType, bodyConfigs);
        // 执行请求，得到http响应类
        return httpRequest.execute();

    }

    private static HttpRequest getHttpRequest(String url, Method method, List headers, List queryStrings,
                                              Integer bodyType, List bodyConfigs) {
        // 0. 设置请求参数   可通过 form表单方法 设置
        if (CollectionUtil.isNotEmpty(queryStrings)) {
            url = getUrl(new StringBuilder(url), queryStrings);
        }
        // 1. 创建HttpRequest对象 - 指定好 url 地址
        HttpRequest httpRequest = new HttpRequest(UrlBuilder.ofHttp(url));
        // 2. 设置请求方式
        httpRequest.setMethod(method);
        // 3. 设置请求头
        //    请求头同样可以逐一设置，也可以封装到map中再统一设置
        //    设置的请求头是否可以覆盖等信息具体请看源码关于重载方法的说明
        httpRequest.header("Authorization", "Bearer test01");
        if (CollectionUtil.isNotEmpty(headers)) {
            httpRequest.addHeaders(listToMap(headers));
        }
        // 设置请求体
        if (CollectionUtil.isNotEmpty(bodyConfigs)) {
            httpRequest.header("Content-Type", "application/json;charset=UTF-8");
            httpRequest.body(getBody(bodyType, bodyConfigs));
        }
        return httpRequest;
    }

    private static String getUrl(StringBuilder url, List<Map<String, Object>> queryStrings) {
        url.append("?");
        for (Map<String, Object> objectMap : queryStrings) {
            url.append(objectMap.get("key") + "=" + objectMap.get("value") + "&");
        }
        return url.substring(0, url.lastIndexOf("&"));
    }

    private static String getBody(Integer bodyType, List bodyConfigs) {
        Map map = listToMap(bodyConfigs);
        return JSON.toJSONString(map);
    }

    public static Map listToMap(List<Map<String, Object>> list) {
        Map map = new HashMap();
        for (Map<String, Object> objectMap : list) {
            map.put(objectMap.get("key"), objectMap.get("value"));
        }
        return map;
    }

}
