package es;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by liwenfeng on 2017/8/3.
 */
public class UpdateIndex {
    public static void main(String[] args) throws Exception {
        updateIndex("liwenfeng","li");

    }
    public static void test(){}

    public static void updateIndex(String clustername , String typename) throws Exception {
        if(clustername.equals("") || clustername == null || typename.equals("") || typename == null){
            throw new Exception("请填写集群名和类型");
        }
        for (int i=0;i<5;i++){
            String j = Integer.toString(i);
            EsClient.client.prepareUpdate(clustername,typename,j)
            .setDoc(
                    jsonBuilder()
                            .startObject()
                            .field("message", "male"+j)
                            .field("address","yunnan"+j)
                            .field("tellphone","tel"+j)
                            .endObject())
                    .get();
        }



    }

}
