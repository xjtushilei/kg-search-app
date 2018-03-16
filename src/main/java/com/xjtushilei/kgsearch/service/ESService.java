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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ESService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<String> indexFragments(List<AssembleFragment> list) {
        RestHighLevelClient client = ESClient.getClient();
        logger.info("start-indexFragments-数量:" + list.size() + "-分批执行次数:" + (list.size() / 500 + 1) + "-每次执行数量:500");
        int index = 0;
        List<String> result = new LinkedList<>();
        while (index < list.size()) {
            BulkRequest request = new BulkRequest();
            int i = 0;
            for (; i < 500 && index < list.size(); i++, index++) {
                AssembleFragment fragment = list.get(index);
                request.add(new IndexRequest("kg-search", "fragments", String.valueOf(fragment.getFragmentID()))
                        .source(JsonUtils.toJson(fragment), XContentType.JSON));
            }
            request.timeout(TimeValue.timeValueMinutes(5));
            try {
                BulkResponse indexResponse = client.bulk(request);
                String info = "success-第" + ((index - 1) / 500 + 1) + "批-end-indexFragments-数量:" + i + "-耗时:" + indexResponse.getTook() + "-结果:" + indexResponse.status();
                result.add(info);
                logger.debug(info);
            } catch (IOException e) {
                e.printStackTrace();
                String info = "error-第" + ((index - 1) / 500 + 1) + "批-end-indexFragments-数量:" + i;
                result.add(info);
                logger.error(info, e);

            }
        }
        return result;
    }

    public List<String> indexTreeDara(Map<String, Object> map) {
        LinkedList<String> result = new LinkedList<>();

        RestHighLevelClient client = ESClient.getClient();

        List<Map<String, String>> list = (List<Map<String, String>>) map.get("data");
        List<String> error = (List<String>) map.get("error");
        String info = "获取数据库中的数据失败-数量:" + error.size() + "-具体为:" + error.toString();
        logger.info(info);
        result.add(info);
        info = "start-indexTree-数量:" + list.size() + "-分批执行次数:" + (list.size() / 20 + 1) + "-每次执行数量:20";
        logger.info(info);
        result.add(info);
        int index = 0;
        while (index < list.size()) {
            BulkRequest request = new BulkRequest();
            int i = 0;
            for (; i < 20 && index < list.size(); i++, index++) {
                Map<String, String> oneTree = list.get(i);
                String ClassName = oneTree.get("ClassName");
                String TermName = oneTree.get("TermName");
                String data = oneTree.get("data");
                request.add(new IndexRequest("kg-search", "tree", ClassName + TermName)
                        .source(data, XContentType.JSON));
            }
            request.timeout(TimeValue.timeValueMinutes(5));
            try {
                BulkResponse indexResponse = client.bulk(request);
                info = "success-第" + ((index - 1) / 20 + 1) + "批-end-indexTree-数量:" + i + "-耗时:" + indexResponse.getTook() + "-结果:" + indexResponse.status();
                logger.debug(info);
                result.add(info);
            } catch (IOException e) {
                e.printStackTrace();
                info = "error-第" + ((index - 1) / 20 + 1) + "批-end-indexTree-数量:" + i;
                logger.error(info, e);
                result.add(info);
            }
        }
        return result;
    }


}
