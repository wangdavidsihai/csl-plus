package com.csl.plus.rms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.TechRequirementAppAreaMapper;
import com.csl.plus.rms.entity.TechRequirementAppArea;
import com.csl.plus.rms.service.ITechRequirementAppAreaService;

import javax.annotation.Resource;
import java.util.Date;


@Service("techRequirementAppAreaService")
public class TechRequirementAppAreaServiceImpl extends ServiceImpl<TechRequirementAppAreaMapper, TechRequirementAppArea> implements ITechRequirementAppAreaService {

    @Resource
    private TechRequirementAppAreaMapper techRequirementAppAreaMapper;
    
    @Transactional
    public boolean saves(TechRequirementAppArea entity){
    	entity.setCreateDate(new Date());
        techRequirementAppAreaMapper.insert(entity);
        return true;
    }

}