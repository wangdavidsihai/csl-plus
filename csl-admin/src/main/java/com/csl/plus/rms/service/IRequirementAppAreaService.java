package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.RequirementAppArea;


/**
 * 资源应用领域表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2020-01-04 20:03:44
 */
public interface IRequirementAppAreaService extends IService<RequirementAppArea> {

    
    boolean saves(RequirementAppArea entity);
}

