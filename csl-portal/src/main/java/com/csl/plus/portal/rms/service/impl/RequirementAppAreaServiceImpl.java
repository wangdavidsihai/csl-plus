package com.csl.plus.portal.rms.service.impl;

import com.csl.plus.portal.rms.service.IRequirementAppAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.RequirementAppAreaMapper;
import com.csl.plus.rms.entity.RequirementAppArea;


import javax.annotation.Resource;
import java.util.Date;


@Service("requirementAppAreaService")
public class RequirementAppAreaServiceImpl extends ServiceImpl<RequirementAppAreaMapper, RequirementAppArea> implements IRequirementAppAreaService {

    @Resource
    private RequirementAppAreaMapper requirementAppAreaMapper;
    
    @Transactional
    public boolean saves(RequirementAppArea entity){
    	entity.setCreateDate(new Date());
        requirementAppAreaMapper.insert(entity);
        return true;
    }

}