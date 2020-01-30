package com.csl.plus.portal.rms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.rms.service.IFinRequirementService;
import com.csl.plus.rms.entity.FinRequirement;
import com.csl.plus.rms.mapper.FinRequirementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("finRequirementService")
public class FinRequirementServiceImpl extends ServiceImpl<FinRequirementMapper, FinRequirement> implements IFinRequirementService {

    @Resource
    private FinRequirementMapper finRequirementMapper;
    
    @Transactional
    public boolean saves(FinRequirement entity){
    	entity.setCreateDate(new Date());
        finRequirementMapper.insert(entity);
        return true;
    }

}