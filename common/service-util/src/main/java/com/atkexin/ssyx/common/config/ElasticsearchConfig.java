package com.atkexin.ssyx.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("Elasticsearch")
public class ElasticsearchConfig {
    @Autowired
    private DataSource dataSource;
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 配置 Elasticsearch 连接信息
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );
        RestHighLevelClient client = new RestHighLevelClient(builder);

        return client;
    }
}