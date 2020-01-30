package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.TechRequirementMapper;
import com.csl.plus.rms.entity.TechRequirement;
import com.csl.plus.rms.service.ITechRequirementService;

import javax.annotation.Resource;
import java.util.Date;


@Service("techRequirementService")
public class TechRequirementServiceImpl extends ServiceImpl<TechRequirementMapper, TechRequirement> implements ITechRequirementService {

    @Resource
    private TechRequirementMapper techRequirementMapper;
    
    @Transactional
    public boolean saves(TechRequirement entity){
    	entity.setCreateDate(new Date());
        techRequirementMapper.insert(entity);
        return true;
    }

}