package com.xjtushilei.kgsearch.service;

import com.xjtushilei.kgsearch.utils.ESClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

@Service
public class ESService {
    RestHighLevelClient restHighLevelClient = ESClient.getClient();


}
