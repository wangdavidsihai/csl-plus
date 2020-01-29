package com.csl.plus.portal.rms.service.impl;

import com.csl.plus.portal.rms.service.IPrdRequirementAppAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.rms.mapper.PrdRequirementAppAreaMapper;
import com.csl.plus.rms.entity.PrdRequirementAppArea;

import javax.annotation.Resource;
import java.util.Date;


@Service("prdRequirementAppAreaService")
public class PrdRequirementAppAreaServiceImpl extends ServiceImpl<PrdRequirementAppAreaMapper, PrdRequirementAppArea> implements IPrdRequirementAppAreaService {

    @Resource
    private PrdRequirementAppAreaMapper prdRequirementAppAreaMapper;
    
    @Transactional
    public boolean saves(PrdRequirementAppArea entity){
    	entity.setCreateDate(new Date());
        prdRequirementAppAreaMapper.insert(entity);
        return true;
    }

}