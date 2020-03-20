package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResProjProduction;
import com.csl.plus.res.mapper.ResProjProductionMapper;
import com.csl.plus.res.service.IResProjProductionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resProjProductionService")
public class ResProjProductionServiceImpl extends ServiceImpl<ResProjProductionMapper, ResProjProduction> implements IResProjProductionService {

    @Resource
    private ResProjProductionMapper resProjProductionMapper;

    @Transactional
    public boolean saves(ResProjProduction entity) {
        entity.setCreateDate(new Date());
        resProjProductionMapper.insert(entity);
        return true;
    }

    @Override
    public List<ResProjProduction> getList() {

        return resProjProductionMapper.getList();
    }

}