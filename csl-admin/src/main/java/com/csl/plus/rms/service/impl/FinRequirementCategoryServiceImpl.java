package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.FinRequirementCategoryMapper;
import com.csl.plus.rms.entity.FinRequirementCategory;
import com.csl.plus.rms.service.IFinRequirementCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("finRequirementCategoryService")
public class FinRequirementCategoryServiceImpl extends ServiceImpl<FinRequirementCategoryMapper, FinRequirementCategory> implements IFinRequirementCategoryService {

    @Resource
    private FinRequirementCategoryMapper finRequirementCategoryMapper;
    
    @Transactional
    public boolean saves(FinRequirementCategory entity){
    	entity.setCreateDate(new Date());
        finRequirementCategoryMapper.insert(entity);
        return true;
    }

}