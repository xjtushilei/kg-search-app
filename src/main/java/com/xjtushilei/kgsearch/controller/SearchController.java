package com.xjtushilei.kgsearch.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/search")
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "关键词", name = "q", defaultValue = "", required = true),
            @ApiImplicitParam(value = "碎片来源", name = "source", defaultValue = "中文维基", required = true),
            @ApiImplicitParam(value = "分面", name = "facet"),
            @ApiImplicitParam(value = "pageNum", name = "pageNum", defaultValue = "1", required = true),
            @ApiImplicitParam(value = "pageSize", name = "pageSize", defaultValue = "10", required = true),
            @ApiImplicitParam(value = "ip", name = "ip", defaultValue = "0.0.0.0"),
            @ApiImplicitParam(value = "ip城市", name = "city", defaultValue = "陕西-西安~")
    })
    public ResponseEntity search(String q,
                                 String source,
                                 String facet,
                                 int pageNum,
                                 int pageSize,
                                 String ip,
                                 String city) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));

        BoolQueryBuilder boolq = new BoolQueryBuilder();
        if ("".equals(q)) {
            boolq.must(QueryBuilders.matchAllQuery());
        } else {
            boolq.must(QueryBuilders.multiMatchQuery(q, "newsTitle", "newsContent"));
        }


        return null;
    }
}
