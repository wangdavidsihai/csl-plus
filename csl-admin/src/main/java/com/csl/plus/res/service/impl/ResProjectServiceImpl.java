package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResProject;
import com.csl.plus.res.mapper.ResProjectMapper;
import com.csl.plus.res.service.IResProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resProjProductionService")
public class ResProjectServiceImpl extends ServiceImpl<ResProjectMapper, ResProject> implements IResProjectService {

    @Resource
    private ResProjectMapper resProjProductionMapper;

    @Transactional
    public boolean saves(ResProject entity) {
        entity.setCreateDate(new Date());
        resProjProductionMapper.insert(entity);
        return true;
    }

    @Override
    public List<ResProject> getList() {

        return resProjProductionMapper.getList();
    }

}