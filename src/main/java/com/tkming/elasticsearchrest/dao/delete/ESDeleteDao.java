package com.tkming.elasticsearchrest.dao.delete;

import com.tkming.elasticsearchrest.dao.AbstractDao;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.springframework.stereotype.Repository;

/**
 * Created by SEELE on 2017/11/14.
 */
@Repository
public class ESDeleteDao extends AbstractDao{

    public DeleteResponse delete(String index,String type,String id){
        DeleteRequest request = new DeleteRequest(index,type,id);
        DeleteResponse response = delete(request);
        ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            System.out.print("not all shard succeed.");
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
                System.out.print("reason: " + reason);
            }
        }

        if (response.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            System.out.print("doc not found.");
        }
        return response;
    }


    public void deleteAsync(String index,String type,String id){
        DeleteRequest request = new DeleteRequest(index,type,id);
        deleteAsync(request);
    }

}
