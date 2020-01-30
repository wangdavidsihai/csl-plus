package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.FinRequirement;


/**
 * 金融需求表
 *
 * @author David
 * @email 
 * @date 2020-01-30 16:15:33
 */
public interface IFinRequirementService extends IService<FinRequirement> {

    
    boolean saves(FinRequirement entity);
}

