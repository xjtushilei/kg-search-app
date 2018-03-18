package com.xjtushilei.kgsearch.utils;

import com.xjtushilei.kgsearch.common.ESConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {
    public static RestHighLevelClient getClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(ESConfig.host, ESConfig.port, "http")));
    }
}
