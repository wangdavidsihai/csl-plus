package com.csl.plus.portal.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.FinRequirementAppArea;


/**
 * 金融应用领域表
 *
 * @author David
 * @email 
 * @date 2020-01-30 16:15:33
 */
public interface IFinRequirementAppAreaService extends IService<FinRequirementAppArea> {

    
    boolean saves(FinRequirementAppArea entity);
}

