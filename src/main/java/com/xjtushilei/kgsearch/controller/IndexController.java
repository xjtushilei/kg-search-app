package com.xjtushilei.kgsearch.controller;

import com.xjtushilei.kgsearch.service.ESIndexService;
import com.xjtushilei.kgsearch.service.YottaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ESIndexService esService;

    @Autowired
    YottaService yottaService;

    @RequestMapping(value = "/fragments", method = RequestMethod.POST)
    @ApiOperation(value = "索引碎片", notes = "")
    @ApiImplicitParam(value = "课程名字", name = "className", defaultValue = "计算机组成原理", required = true)
    public List<String> indexFragments(String className) {
        return esService.indexFragments(yottaService.getAllByClassName(className));
    }

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ApiOperation(value = "索引树型结构", notes = "")
    @ApiImplicitParam(value = "课程名字", name = "className", defaultValue = "计算机组成原理", required = true)
    public ResponseEntity indexTree(String className) {
        try {
            return ResponseEntity.ok().body(esService.indexTreeDara(yottaService.getAllTermNameByClassName(className)));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/getAllClassName", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的课程名字", notes = "")
    public ResponseEntity getAllClassName() {
        return ResponseEntity.ok().body(yottaService.getAllClassName());
    }

    @RequestMapping(value = "/indexMapping", method = RequestMethod.POST)
    @ApiOperation(value = "设置索引结构", notes = "只有第一次时候（elasticsearch中没有任何数据）才可以运行！")
    public ResponseEntity indexMapping() {
        return ResponseEntity.ok().body(yottaService.getAllClassName());
    }

}
