package com.xjtushilei.kgsearch;

import com.xjtushilei.kgsearch.mapper.YottaMapper;
import com.xjtushilei.kgsearch.service.ESIndexService;
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
    ESIndexService esService;

    @Autowired
    YottaService yottaService;

    @Autowired
    YottaMapper yottaMapper;

    @Test
    public void test1() throws IOException {
        esService.indexFragments(yottaService.getAllByClassName("计算机组成原理"));
    }

    @Test
    public void test2() {
        yottaMapper.findTermNameByClassName("计算机组成原理").forEach(s -> System.out.println(s));
    }

    @Test
    public void test3() throws IOException {
        esService.indexTreeDara(yottaService.getAllTermNameByClassName("计算机组成原理")).forEach(s -> System.out.println(s));
    }

}
