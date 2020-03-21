package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResProjectData;
import com.csl.plus.res.mapper.ResProjectDataMapper;
import com.csl.plus.res.service.IResProjectDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProjProductionDataService")
public class ResProjectDataServiceImpl extends ServiceImpl<ResProjectDataMapper, ResProjectData> implements IResProjectDataService {

    @Resource
    private ResProjectDataMapper resProjProductionDataMapper;

    @Transactional
    public boolean saves(ResProjectData entity) {
        entity.setCreateDate(new Date());
        resProjProductionDataMapper.insert(entity);
        return true;
    }

}