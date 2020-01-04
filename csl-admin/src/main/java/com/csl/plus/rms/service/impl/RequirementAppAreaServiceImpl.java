package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.RequirementAppAreaMapper;
import com.csl.plus.rms.entity.RequirementAppArea;
import com.csl.plus.rms.service.IRequirementAppAreaService;

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