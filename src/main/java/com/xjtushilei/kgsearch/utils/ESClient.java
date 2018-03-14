package com.xjtushilei.kgsearch.utils;

import com.xjtushilei.kgsearch.common.ESConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {
    private static volatile RestHighLevelClient restHighLevelClient;

    public static RestHighLevelClient getClient() {


        if (restHighLevelClient == null) {
            synchronized (ESClient.class) {
                if (restHighLevelClient == null)
                    return new RestHighLevelClient(RestClient.builder(new HttpHost(ESConfig.host, ESConfig.port, "http")));
            }
        }
        return restHighLevelClient;
    }
}
