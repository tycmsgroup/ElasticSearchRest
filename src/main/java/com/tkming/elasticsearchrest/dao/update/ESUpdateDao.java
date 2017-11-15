package com.tkming.elasticsearchrest.dao.update;

import com.tkming.elasticsearchrest.dao.AbstractDao;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class ESUpdateDao extends AbstractDao {

    public UpdateResponse update(String index,String type,String id,String source){
        UpdateRequest request = new UpdateRequest(index,type,id);
        request.doc(source, XContentType.JSON);
        UpdateResponse updateResponse = update(request);
        return updateResponse;
    }

    public UpdateResponse update(String index,String type,String id,Map map){
        UpdateRequest request = new UpdateRequest(index,type,id);
        request.doc(map);
        UpdateResponse updateResponse = update(request);
        return updateResponse;
    }

    public UpdateResponse update(String index,String type,String id,XContentBuilder xContentBuilder){
        UpdateRequest request = new UpdateRequest(index,type,id);
        request.doc(xContentBuilder);
        UpdateResponse updateResponse = update(request);
        return updateResponse;
    }

    public void updateAsync(String index,String type,String id,String source){
        UpdateRequest request = new UpdateRequest(index,type,id);
        request.doc(source, XContentType.JSON);
        updateAsync(request);
    }

    public void updateAsync(String index,String type,String id,Map map){
        UpdateRequest request = new UpdateRequest(index,type,id);
        request.doc(map);
        updateAsync(request);
    }

    public void updateAsync(String index,String type,String id,XContentBuilder xContentBuilder){
        UpdateRequest request = new UpdateRequest(index,type,id);
        request.doc(xContentBuilder);
        updateAsync(request);
    }


}
