package com.csl.plus.portal.rms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.rms.service.ITechRequirementAppAreaService;
import com.csl.plus.rms.entity.TechRequirementAppArea;
import com.csl.plus.rms.mapper.TechRequirementAppAreaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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