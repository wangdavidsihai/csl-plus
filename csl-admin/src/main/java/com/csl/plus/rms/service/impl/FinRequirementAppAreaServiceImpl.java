package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.FinRequirementAppAreaMapper;
import com.csl.plus.rms.entity.FinRequirementAppArea;
import com.csl.plus.rms.service.IFinRequirementAppAreaService;

import javax.annotation.Resource;
import java.util.Date;


@Service("finRequirementAppAreaService")
public class FinRequirementAppAreaServiceImpl extends ServiceImpl<FinRequirementAppAreaMapper, FinRequirementAppArea> implements IFinRequirementAppAreaService {

    @Resource
    private FinRequirementAppAreaMapper finRequirementAppAreaMapper;
    
    @Transactional
    public boolean saves(FinRequirementAppArea entity){
    	entity.setCreateDate(new Date());
        finRequirementAppAreaMapper.insert(entity);
        return true;
    }

}