package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResProjProductionService;
import com.csl.plus.res.entity.ResProject;
import com.csl.plus.res.mapper.ResProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProjProductionService")
public class ResProjProductionServiceImpl extends ServiceImpl<ResProjectMapper, ResProject> implements IResProjProductionService {

    @Resource
    private ResProjectMapper resProjProductionMapper;

    @Transactional
    public boolean saves(ResProject entity) {
        entity.setCreateDate(new Date());
        resProjProductionMapper.insert(entity);
        return true;
    }

}