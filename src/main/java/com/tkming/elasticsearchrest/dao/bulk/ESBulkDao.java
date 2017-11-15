package com.tkming.elasticsearchrest.dao.bulk;

import com.tkming.elasticsearchrest.dao.AbstractDao;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ESBulkDao extends AbstractDao{

    public BulkResponse bulk(List<Object> indexRequests){
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < indexRequests.size(); i++) {
            if(indexRequests.get(i) instanceof IndexRequest){
                request.add((IndexRequest)indexRequests.get(i));
            }else if (indexRequests.get(i) instanceof UpdateRequest){
                request.add((UpdateRequest)indexRequests.get(i));
            }else if(indexRequests.get(i) instanceof DeleteRequest){
                request.add((DeleteRequest)indexRequests.get(i));
            }
        }
        BulkResponse bulkResponse  = bulk(request);
        return bulkResponse;
    }

    public void bulkAsync(List<Object> indexRequests){
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < indexRequests.size(); i++) {
            if(indexRequests.get(i) instanceof IndexRequest){
                request.add((IndexRequest)indexRequests.get(i));
            }else if (indexRequests.get(i) instanceof UpdateRequest){
                request.add((UpdateRequest)indexRequests.get(i));
            }else if(indexRequests.get(i) instanceof DeleteRequest){
                request.add((DeleteRequest)indexRequests.get(i));
            }
        }
        bulkAsync(request);
    }

}
