package com.csl.plus.portal.rms.service.impl;

import com.csl.plus.portal.rms.service.IRequirementCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.RequirementCategoryMapper;
import com.csl.plus.rms.entity.RequirementCategory;

import javax.annotation.Resource;
import java.util.Date;


@Service("requirementCategoryService")
public class RequirementCategoryServiceImpl extends ServiceImpl<RequirementCategoryMapper, RequirementCategory> implements IRequirementCategoryService {

    @Resource
    private RequirementCategoryMapper requirementCategoryMapper;
    
    @Transactional
    public boolean saves(RequirementCategory entity){
    	entity.setCreateDate(new Date());
        requirementCategoryMapper.insert(entity);
        return true;
    }

}