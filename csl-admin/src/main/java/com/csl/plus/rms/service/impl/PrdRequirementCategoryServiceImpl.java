package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.PrdRequirementCategoryMapper;
import com.csl.plus.rms.entity.PrdRequirementCategory;
import com.csl.plus.rms.service.IPrdRequirementCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("prdRequirementCategoryService")
public class PrdRequirementCategoryServiceImpl extends ServiceImpl<PrdRequirementCategoryMapper, PrdRequirementCategory> implements IPrdRequirementCategoryService {

    @Resource
    private PrdRequirementCategoryMapper prdRequirementCategoryMapper;
    
    @Transactional
    public boolean saves(PrdRequirementCategory entity){
    	entity.setCreateDate(new Date());
        prdRequirementCategoryMapper.insert(entity);
        return true;
    }

}