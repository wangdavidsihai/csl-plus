package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsCategoryMapper;
import com.csl.plus.cms.entity.CmsCategory;
import com.csl.plus.cms.service.ICmsCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsCategoryService")
public class CmsCategoryServiceImpl extends ServiceImpl<CmsCategoryMapper, CmsCategory> implements ICmsCategoryService {

    @Resource
    private CmsCategoryMapper cmsCategoryMapper;
    
    @Transactional
    public boolean saves(CmsCategory entity){
    	entity.setCreateDate(new Date());
        cmsCategoryMapper.insert(entity);
        return true;
    }

}