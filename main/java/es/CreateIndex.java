package es;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.collect.HppcMaps;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * Created by liwenfeng on 2017/8/1.
 */
public class CreateIndex {
    public static void main(String[] args) {
        try {
            createIndex("liwenfeng","li");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void createIndex(String indexName , String typeName) throws Exception {
        if (testIndexExist(indexName)){
            throw new Exception("索引已经存在！！");
        }
        for (int i = 0; i <5; i++) {
            String j = Integer.toString(i);
            IndexResponse response = EsClient.client .prepareIndex(indexName, typeName,j)
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();
        }


    }
    //判断索引是否存在
    public static boolean testIndexExist(String indexName){
        IndicesExistsResponse  response =
                EsClient.client.admin().indices().exists(
                        new IndicesExistsRequest().indices(new String[]{indexName})).actionGet();
        if (response.isExists()){
            return  true;
        }else {
            return false;
        }

    }

}
