package com.xjtushilei.kgsearch.controller;

import com.xjtushilei.kgsearch.service.ESService;
import com.xjtushilei.kgsearch.service.YottaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ESService esService;

    @Autowired
    YottaService yottaService;

    @RequestMapping(value = "/fragments", method = RequestMethod.POST)
    public List<String> indexFragments(@RequestParam(required = true, value = "className", defaultValue = "计算机组成原理") String className) {
        return esService.indexFragments(yottaService.getAllByClassName(className));
    }

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public ResponseEntity indexTree(@RequestParam(required = true, value = "className", defaultValue = "计算机组成原理") String className) {
        try {
            return ResponseEntity.ok().body(esService.indexTreeDara(yottaService.getAllTermNameByClassName(className)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
