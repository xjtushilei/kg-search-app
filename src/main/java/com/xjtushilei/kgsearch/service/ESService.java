package com.xjtushilei.kgsearch.service;

import com.xjtushilei.kgsearch.model.AssembleFragment;
import com.xjtushilei.kgsearch.utils.ESClient;
import com.xjtushilei.kgsearch.utils.JsonUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ESService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void indexFragments(List<AssembleFragment> list) {
        RestHighLevelClient client = ESClient.getClient();
        logger.info("start-indexFragments-数量:" + list.size() + "-分批执行次数:" + list.size() / 500 + "-每次执行数量:500");
        int index = 0;
        while (index < list.size()) {
            BulkRequest request = new BulkRequest();
            int i = 0;
            for (; i < 500 && index < list.size(); i++, index++) {
                AssembleFragment fragment = list.get(index);
                request.add(new IndexRequest("fragments", "doc", String.valueOf(fragment.getFragmentID()))
                        .source(JsonUtils.toJson(fragment), XContentType.JSON));
            }
            request.timeout(TimeValue.timeValueMinutes(5));
            try {
                BulkResponse indexResponse = client.bulk(request);
                logger.info("第" + (index - 1) / 500 + "批-end-indexFragments-数量:" + i + "-耗时:" + indexResponse.getTook() + "-结果:" + indexResponse.status());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("第" + (index - 1) / 500 + "批-end-indexFragments-数量:" + i, e);

            }
        }
    }

}
