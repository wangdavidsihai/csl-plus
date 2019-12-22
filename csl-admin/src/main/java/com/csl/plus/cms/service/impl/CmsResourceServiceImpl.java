package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsResourceMapper;
import com.csl.plus.cms.entity.CmsResource;
import com.csl.plus.cms.service.ICmsResourceService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsResourceService")
public class CmsResourceServiceImpl extends ServiceImpl<CmsResourceMapper, CmsResource> implements ICmsResourceService {

    @Resource
    private CmsResourceMapper cmsResourceMapper;
    
    @Transactional
    public boolean saves(CmsResource entity){
    	entity.setCreateDate(new Date());
        cmsResourceMapper.insert(entity);
        return true;
    }

}