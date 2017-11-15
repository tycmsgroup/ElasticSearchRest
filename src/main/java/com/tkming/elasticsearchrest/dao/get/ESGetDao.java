package com.tkming.elasticsearchrest.dao.get;

import com.tkming.elasticsearchrest.dao.AbstractDao;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.stereotype.Repository;


@Repository
public class ESGetDao extends AbstractDao {

    public GetResponse get(String index,String type,String id){
        GetRequest request = new GetRequest(index,type,id);
        GetResponse response = get(request);
        return response;
    }


    public GetResponse  get(String index,String type,String id,FetchSourceContext fetchSourceContext){
        GetRequest request = new GetRequest(index,type,id);
        request.fetchSourceContext(fetchSourceContext);
        GetResponse response = get(request);
        return response;
    }

    public void getAsync(String index,String type,String id){
        GetRequest request = new GetRequest(index,type,id);
        getAsync(request);
    }


    public void  getAsync(String index,String type,String id,FetchSourceContext fetchSourceContext){
        GetRequest request = new GetRequest(index,type,id);
        request.fetchSourceContext(fetchSourceContext);
        getAsync(request);
    }



}
