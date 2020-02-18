package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResExpertDataService;
import com.csl.plus.res.entity.ResExpertData;
import com.csl.plus.res.mapper.ResExpertDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resExpertDataService")
public class ResExpertDataServiceImpl extends ServiceImpl<ResExpertDataMapper, ResExpertData> implements IResExpertDataService {

    @Resource
    private ResExpertDataMapper resExpertDataMapper;

    @Transactional
    public boolean saves(ResExpertData entity) {
        entity.setCreateDate(new Date());
        resExpertDataMapper.insert(entity);
        return true;
    }

    @Override
    public ResExpertData getExpertDataByExpId(Long expId) {
        return resExpertDataMapper.getExpertDataByExpId(expId);
    }

}