package com.xjtushilei.kgsearch;

import com.xjtushilei.kgsearch.utils.ESClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KgSearchApplicationTests {


    @Test
    public void test1() throws IOException {
        RestHighLevelClient restHighLevelClient = ESClient.getClient();
        System.out.println(restHighLevelClient.info().getClusterName());
    }

}
