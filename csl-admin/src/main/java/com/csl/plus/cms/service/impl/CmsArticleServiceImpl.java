package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsArticleMapper;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.service.ICmsArticleService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsArticleService")
public class CmsArticleServiceImpl extends ServiceImpl<CmsArticleMapper, CmsArticle> implements ICmsArticleService {

    @Resource
    private CmsArticleMapper cmsArticleMapper;
    
    @Transactional
    public boolean saves(CmsArticle entity){
    	entity.setCreateDate(new Date());
        cmsArticleMapper.insert(entity);
        return true;
    }

}