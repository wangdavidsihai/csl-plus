package com.csl.plus.portal.rms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.rms.service.ITechRequirementService;
import com.csl.plus.rms.entity.TechRequirement;
import com.csl.plus.rms.mapper.TechRequirementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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