package com.tkming.elasticsearchrest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tkming.elasticsearchrest.utils.ControllerUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.log4j.Logger;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;

/**
 * Created by TKMing on 2017/11/13.
 */

@RestController
public class Connect {
    private final Logger logger = Logger.getLogger(getClass());

    /**
     *
     */
    private final int[] port = new int[]{9200, 9201};
    /**
     *
     */
    private final int timeout = 10000;
    /**
     *
     */
    private RestClient restClient = RestClient.builder(
            new HttpHost("localhost", port[0], "http"),
            new HttpHost("localhost", port[1], "http")).build();


    /**
     *
     * @return restClient
     */
    public final RestClient getRestClient() {
        return restClient;
    }

    /***
     *
     * @param port 端口号
     * @return httpHost
     */
    public final HttpHost getHttpHost(final int port) {
        HttpHost httpHost = new HttpHost("localhost", port, "http");
        return httpHost;
    }

    public final void setRestClientBuilder(){
        HttpHost httpHost1 = getHttpHost(port[0]);
        HttpHost httpHost2 = getHttpHost(port[1]);
        RestClientBuilder builder = RestClient.builder(httpHost1, httpHost2);

        //设置默认请求头
        BasicHeader basicHeader = new BasicHeader("header", "value");
        Header[] defaultHeaders = new Header[]{basicHeader};
        builder.setDefaultHeaders(defaultHeaders);

        //设置请求超时时间
        builder.setMaxRetryTimeoutMillis(timeout);

        //设置内部监听
        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(HttpHost host) {
                logger.info(host.getAddress() + " had failed.");
            }
        });
    }

    /***
     *
     * @return result
     */
    @RequestMapping(value = "/get")
    public final Object get() {
        Map<String, Object> result = ControllerUtil.defaultSuccResult();

        try {
            Map<String, String> params = Collections.singletonMap("pretty", "true");
            Response response = restClient.performRequest("GET", "/my_index_v4_alias/_search", params);
            result.put("content",response);
            logger.info("get ok");
        }catch (IOException e){
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    /***
     *
     * @return result
     */
    @RequestMapping(value = "/get2")
    public final Object get2() {
        Map<String, Object> result = ControllerUtil.defaultSuccResult();
        JSONObject resultJson = null;
        try {
            Map<String, String> params = Collections.emptyMap();
            HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory consumerFactory =
                    new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024);
            Response response = restClient.performRequest("GET", "/my_index_v4_alias/_search", params, null, consumerFactory);
            HttpEntity entity = response.getEntity();

            if (entity != null){

                InputStream instreams = entity.getContent();

                String str = ConvertStreamToString(instreams);
                resultJson = JSON.parseObject(str);
            }
            JSONObject hits = resultJson.getJSONObject("hits");
            result.put("content",hits);
            logger.info("get2 ok");
        }catch (IOException e){
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    @RequestMapping(value = "/put")
    public final Object put() {
        Map<String, Object> result = ControllerUtil.defaultSuccResult();
        try {
            Map<String, String> params = Collections.emptyMap();
            String jsonString = "{" +
                    "\"user\":\"kimchy\"," +
                    "\"postDate\":\"2013-01-30\"," +
                    "\"message\":\"trying out Elasticsearch\"" +
                    "}";
            HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
            Response response = restClient.performRequest("PUT", "/my_index_v4_alias/doc/1", params, entity);
            JSONObject resultJson = JSON.parseObject(response.toString());
            result.put("content",resultJson);
            logger.info("put ok");
        }catch (IOException e){
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    public static String ConvertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuilder sb = new StringBuilder();

        String line = null;

        try {

            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");

            }

        } catch (IOException e) {

            System.out.println("Error=" + e.toString());

        } finally {

            try {

                is.close();

            } catch (IOException e) {

                System.out.println("Error=" + e.toString());

            }

        }

        return sb.toString();

    }

}
