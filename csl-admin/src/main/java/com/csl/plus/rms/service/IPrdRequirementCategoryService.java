package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.PrdRequirementCategory;


/**
 * 需求类别表
 *
 * @author David
 * @email 
 * @date 2020-01-29 11:36:20
 */
public interface IPrdRequirementCategoryService extends IService<PrdRequirementCategory> {

    
    boolean saves(PrdRequirementCategory entity);
}

