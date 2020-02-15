package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResProjProductionDataMapper;
import com.csl.plus.res.entity.ResProjProductionData;
import com.csl.plus.res.service.IResProjProductionDataService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProjProductionDataService")
public class ResProjProductionDataServiceImpl extends ServiceImpl<ResProjProductionDataMapper, ResProjProductionData> implements IResProjProductionDataService {

    @Resource
    private ResProjProductionDataMapper resProjProductionDataMapper;
    
    @Transactional
    public boolean saves(ResProjProductionData entity){
    	entity.setCreateDate(new Date());
        resProjProductionDataMapper.insert(entity);
        return true;
    }

}