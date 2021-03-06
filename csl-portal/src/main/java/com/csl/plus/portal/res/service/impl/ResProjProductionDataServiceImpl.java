package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResProjProductionDataService;
import com.csl.plus.res.entity.ResProjectData;
import com.csl.plus.res.mapper.ResProjectDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProjProductionDataService")
public class ResProjProductionDataServiceImpl extends ServiceImpl<ResProjectDataMapper, ResProjectData> implements IResProjProductionDataService {

    @Resource
    private ResProjectDataMapper resProjProductionDataMapper;

    @Transactional
    public boolean saves(ResProjectData entity) {
        entity.setCreateDate(new Date());
        resProjProductionDataMapper.insert(entity);
        return true;
    }

}