package com.csl.plus.portal.rms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.rms.service.IFinRequirementAppAreaService;
import com.csl.plus.rms.entity.FinRequirementAppArea;
import com.csl.plus.rms.mapper.FinRequirementAppAreaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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