package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.PrdRequirementAppArea;


/**
 * 资源应用领域表
 *
 * @author David
 * @email 
 * @date 2020-01-29 11:36:20
 */
public interface IPrdRequirementAppAreaService extends IService<PrdRequirementAppArea> {

    
    boolean saves(PrdRequirementAppArea entity);
}

