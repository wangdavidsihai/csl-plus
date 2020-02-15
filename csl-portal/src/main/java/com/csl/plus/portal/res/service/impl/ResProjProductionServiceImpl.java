package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResProjProductionService;
import com.csl.plus.res.entity.ResProjProduction;
import com.csl.plus.res.mapper.ResProjProductionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


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

}