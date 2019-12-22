package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsArticleDataMapper;
import com.csl.plus.cms.entity.CmsArticleData;
import com.csl.plus.cms.service.ICmsArticleDataService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsArticleDataService")
public class CmsArticleDataServiceImpl extends ServiceImpl<CmsArticleDataMapper, CmsArticleData> implements ICmsArticleDataService {

    @Resource
    private CmsArticleDataMapper cmsArticleDataMapper;
    
    @Transactional
    public boolean saves(CmsArticleData entity){
    	entity.setCreateDate(new Date());
        cmsArticleDataMapper.insert(entity);
        return true;
    }

}