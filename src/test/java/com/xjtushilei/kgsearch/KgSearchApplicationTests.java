package com.xjtushilei.kgsearch;

import com.xjtushilei.kgsearch.service.ESService;
import com.xjtushilei.kgsearch.service.YottaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KgSearchApplicationTests {

    @Autowired
    ESService esService;

    @Autowired
    YottaService yottaService;

    @Test
    public void test1() throws IOException {
        esService.indexFragments(yottaService.getAllByClassName("计算机组成原理"));
    }

}
