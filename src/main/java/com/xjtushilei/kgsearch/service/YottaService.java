package com.xjtushilei.kgsearch.service;

import com.github.pagehelper.PageHelper;
import com.xjtushilei.kgsearch.mapper.YottaMapper;
import com.xjtushilei.kgsearch.model.AssembleFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YottaService {
    @Autowired
    YottaMapper yottaMapper;

    public List<AssembleFragment> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return yottaMapper.findAll();
    }
}