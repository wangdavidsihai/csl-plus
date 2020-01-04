package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.RequirementCategoryMapper;
import com.csl.plus.rms.entity.RequirementCategory;
import com.csl.plus.rms.service.IRequirementCategoryService;

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