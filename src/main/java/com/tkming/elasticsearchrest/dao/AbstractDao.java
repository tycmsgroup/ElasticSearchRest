package com.tkming.elasticsearchrest.dao;

import org.apache.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by SEELE on 2017/11/14.
 */
public class AbstractDao {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public IndexResponse index(IndexRequest request){
        try {
            IndexResponse indexResponse = restHighLevelClient.index(request);
            return indexResponse;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    public void indexAsync(IndexRequest request){
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                logger.info(request.toString() +", succeed");
            }

            @Override
            public void onFailure(Exception e) {
                logger.info(request.toString() +", failed");
            }
        };
        restHighLevelClient.indexAsync(request,listener);
    }


    public GetResponse get(GetRequest request){
        try {
            GetResponse response = restHighLevelClient.get(request);
            return response;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    public void getAsync(GetRequest request){
        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getFields) {
                logger.info(request.toString() +", succeed");
            }

            @Override
            public void onFailure(Exception e) {
                logger.info(request.toString() +", failed");
            }
        };

        restHighLevelClient.getAsync(request, listener);
    }

    public DeleteResponse delete(DeleteRequest request){
        try {
            DeleteResponse response = restHighLevelClient.delete(request);
            return response;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.CONFLICT) {
                logger.error("conflict.");
            }
            return null;
        }
    }

    public void deleteAsync(DeleteRequest request){
        ActionListener<DeleteResponse> listener = new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                logger.info(request.toString() +", succeed");
            }

            @Override
            public void onFailure(Exception e) {
                logger.info(request.toString() +", failed");
            }
        };
        restHighLevelClient.deleteAsync(request, listener);
    }


    public UpdateResponse update(UpdateRequest request){
        try {
            UpdateResponse Response = restHighLevelClient.update(request);
            return Response;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    public void updateAsync(UpdateRequest request){
        ActionListener<UpdateResponse> listener = new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                logger.info(request.toString() +", succeed");
            }

            @Override
            public void onFailure(Exception e) {
                logger.info(request.toString() +", failed");
            }
        };
        restHighLevelClient.updateAsync(request, listener);
    }

    public BulkResponse bulk(BulkRequest request){
        try {
            BulkResponse bulkResponse = restHighLevelClient.bulk(request);
            return bulkResponse;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    public void bulkAsync(BulkRequest request){
        ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkResponse) {
                logger.info(request.toString() +", succeed");
            }

            @Override
            public void onFailure(Exception e) {
                logger.info(request.toString() +", failed");
            }
        };
        restHighLevelClient.bulkAsync(request,listener);
    }
}
