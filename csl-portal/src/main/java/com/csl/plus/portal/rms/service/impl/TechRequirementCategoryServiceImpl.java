package com.csl.plus.portal.rms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.rms.service.ITechRequirementCategoryService;
import com.csl.plus.rms.entity.TechRequirementCategory;
import com.csl.plus.rms.mapper.TechRequirementCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("techRequirementCategoryService")
public class TechRequirementCategoryServiceImpl extends ServiceImpl<TechRequirementCategoryMapper, TechRequirementCategory> implements ITechRequirementCategoryService {

    @Resource
    private TechRequirementCategoryMapper techRequirementCategoryMapper;
    
    @Transactional
    public boolean saves(TechRequirementCategory entity){
    	entity.setCreateDate(new Date());
        techRequirementCategoryMapper.insert(entity);
        return true;
    }

}