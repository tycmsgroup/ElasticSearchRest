package com.tkming.elasticsearchrest.dao.index;

import com.tkming.elasticsearchrest.dao.AbstractDao;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class ESIndexDao extends AbstractDao {

    public IndexResponse index(String index,String type,String id,String source){
        IndexRequest request = new IndexRequest(index,type,id);
        request.source(source, XContentType.JSON);
        IndexResponse indexResponse = index(request);
        return indexResponse;
    }

    public IndexResponse index(String index,String type,String id,Map map){
        IndexRequest request = new IndexRequest(index,type,id);
        request.source(map);
        IndexResponse indexResponse = index(request);
        return indexResponse;
    }

    public void indexAsync(String index,String type,String id,String source){
        IndexRequest request = new IndexRequest(index,type,id);
        request.source(source, XContentType.JSON);
        indexAsync(request);
    }

    public void indexAsync(String index,String type,String id,Map map){
        IndexRequest request = new IndexRequest(index,type,id);
        request.source(map);
        indexAsync(request);
    }


}
