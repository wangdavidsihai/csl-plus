package com.csl.plus.portal.rms.service.impl;

import com.csl.plus.portal.rms.service.IRequirementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.RequirementMapper;
import com.csl.plus.rms.entity.Requirement;

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