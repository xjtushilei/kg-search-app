package com.xjtushilei.kgsearch.controller;

import com.xjtushilei.kgsearch.common.ESConfig;
import com.xjtushilei.kgsearch.model.AssembleFragment;
import com.xjtushilei.kgsearch.service.YottaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testAll {

    @Autowired
    YottaService yottaService;


    @GetMapping("/getall")
    public List<AssembleFragment> test1(int pageNum, int pageSize) {
        return yottaService.getAll(pageNum, pageSize);
    }

    @GetMapping("/get2")
    public String test2() {
        return ESConfig.host;
    }
}
