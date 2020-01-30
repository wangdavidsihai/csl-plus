package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.TechRequirement;


/**
 * 需求表
 *
 * @author David
 * @email 
 * @date 2020-01-30 10:58:45
 */
public interface ITechRequirementService extends IService<TechRequirement> {

    
    boolean saves(TechRequirement entity);
}

