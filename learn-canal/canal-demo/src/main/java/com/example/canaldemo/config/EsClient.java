//package com.example.canaldemo.config;
//
//import jdk.nashorn.internal.objects.annotations.Setter;
//import lombok.Data;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.ElasticsearchClient;
//import org.elasticsearch.client.RestClient;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author wfh
// * @create 2023/4/12 15:50
// */
//@Data
//@ConfigurationProperties(prefix = "custom.elasticsearch")
//@Configuration
//public class EsClient {
//
//    /**
//     * 主机
//     */
//    private String host;
//
//    /**
//     * 端口
//     */
//    private Integer port;
//
//    /**
//     * 用户名
//     */
//    private String username;
//
//    /**
//     * 密码
//     */
//    private String password;
//
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(
//                AuthScope.ANY, new UsernamePasswordCredentials(username, password));
//
//        // Create the low-level client
//        RestClient restClient = RestClient.builder(new HttpHost(host, port))
//                .setHttpClientConfigCallback(httpAsyncClientBuilder ->
//                        httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
//                .build();
//        // Create the transport with a Jackson mapper
//        RestClientTransport transport = new RestClientTransport(
//                restClient, new JacksonJsonpMapper());
//        // Create the transport with a Jackson mapper
//        return new ElasticsearchClient(transport);
//    }
//}
