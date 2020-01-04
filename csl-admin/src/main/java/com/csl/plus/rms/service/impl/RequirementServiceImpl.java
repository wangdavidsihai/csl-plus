package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.RequirementMapper;
import com.csl.plus.rms.entity.Requirement;
import com.csl.plus.rms.service.IRequirementService;

import javax.annotation.Resource;
import java.util.Date;


@Service("requirementService")
public class RequirementServiceImpl extends ServiceImpl<RequirementMapper, Requirement> implements IRequirementService {

    @Resource
    private RequirementMapper requirementMapper;
    
    @Transactional
    public boolean saves(Requirement entity){
    	entity.setCreateDate(new Date());
        requirementMapper.insert(entity);
        return true;
    }

}