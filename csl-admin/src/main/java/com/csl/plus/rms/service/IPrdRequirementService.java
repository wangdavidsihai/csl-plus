package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.PrdRequirement;


/**
 * 需求表
 *
 * @author David
 * @email 
 * @date 2020-01-29 11:36:20
 */
public interface IPrdRequirementService extends IService<PrdRequirement> {

    
    boolean saves(PrdRequirement entity);
}

