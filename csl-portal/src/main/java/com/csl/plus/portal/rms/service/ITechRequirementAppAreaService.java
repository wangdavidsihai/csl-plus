package com.csl.plus.portal.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.TechRequirementAppArea;


/**
 * 技术应用领域表
 *
 * @author David
 * @email 
 * @date 2020-01-30 10:58:45
 */
public interface ITechRequirementAppAreaService extends IService<TechRequirementAppArea> {

    
    boolean saves(TechRequirementAppArea entity);
}

