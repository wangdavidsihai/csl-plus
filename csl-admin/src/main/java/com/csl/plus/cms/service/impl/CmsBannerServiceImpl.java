package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsBannerMapper;
import com.csl.plus.cms.entity.CmsBanner;
import com.csl.plus.cms.service.ICmsBannerService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsBannerService")
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner> implements ICmsBannerService {

    @Resource
    private CmsBannerMapper cmsBannerMapper;
    
    @Transactional
    public boolean saves(CmsBanner entity){
    	entity.setCreateDate(new Date());
        cmsBannerMapper.insert(entity);
        return true;
    }

}