package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.PrdRequirementMapper;
import com.csl.plus.rms.entity.PrdRequirement;
import com.csl.plus.rms.service.IPrdRequirementService;

import javax.annotation.Resource;
import java.util.Date;


@Service("prdRequirementService")
public class PrdRequirementServiceImpl extends ServiceImpl<PrdRequirementMapper, PrdRequirement> implements IPrdRequirementService {

    @Resource
    private PrdRequirementMapper prdRequirementMapper;
    
    @Transactional
    public boolean saves(PrdRequirement entity){
    	entity.setCreateDate(new Date());
        prdRequirementMapper.insert(entity);
        return true;
    }

}