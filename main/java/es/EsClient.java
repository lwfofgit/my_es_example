package es;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * Created by liwenfeng on 2017/8/1.
 */
public class EsClient {

    public static String CLUSTER_NAME = "elasticsearch";
    public static String CONNECTION_ADDRESS = "127.0.0.1:9300";
    public static Client client = null;

    static {
        try {
            client = getClint(CLUSTER_NAME,CONNECTION_ADDRESS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static Client getClint(String clusterName, String connectionAddress) throws Exception {
        if (clusterName == null || clusterName.equals("")  || connectionAddress == null || connectionAddress.equals("")) {
            throw new Exception("集群名和连接地址没有设置！！！");
        }

        //如果集群名字不是默认的“elasticsearch”,则需要设置集群名字
/*        Settings settings = Settings.settingsBuilder()
                .put("cluster.name",clusterName)
                .build();
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300));*/

        String[] connect = connectionAddress.split(",");
        for (String ipSr : connect) {
            String[] connectionAdr = ipSr.split(":");
            if (connectionAdr.length == 2) {
                 client = TransportClient.builder().build()
                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300));
            }
        }

        return  client;
    }


    public static void main(String[] args) {

    }
}
