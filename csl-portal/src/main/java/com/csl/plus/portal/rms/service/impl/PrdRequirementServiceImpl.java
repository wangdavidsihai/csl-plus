package com.csl.plus.portal.rms.service.impl;

import com.csl.plus.portal.rms.service.IPrdRequirementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.PrdRequirementMapper;
import com.csl.plus.rms.entity.PrdRequirement;

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