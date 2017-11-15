package com.tkming.elasticsearchrest.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

    @Value("${myProps.address}")
    private String[] address ;
    @Value("${myProps.port}")
    private int[] port ;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClient restClient = RestClient.builder(
                new HttpHost(address[0], port[0], "http"),
                new HttpHost(address[1], port[1], "http")).build();
        RestHighLevelClient client = new RestHighLevelClient(restClient);
        return client;
    }
}
