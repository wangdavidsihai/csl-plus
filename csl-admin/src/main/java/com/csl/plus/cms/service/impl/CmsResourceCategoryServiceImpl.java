package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsResourceCategoryMapper;
import com.csl.plus.cms.entity.CmsResourceCategory;
import com.csl.plus.cms.service.ICmsResourceCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsResourceCategoryService")
public class CmsResourceCategoryServiceImpl extends ServiceImpl<CmsResourceCategoryMapper, CmsResourceCategory> implements ICmsResourceCategoryService {

    @Resource
    private CmsResourceCategoryMapper cmsResourceCategoryMapper;
    
    @Transactional
    public boolean saves(CmsResourceCategory entity){
    	entity.setCreateDate(new Date());
        cmsResourceCategoryMapper.insert(entity);
        return true;
    }

}