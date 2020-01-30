package com.csl.plus.portal.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.TechRequirementCategory;


/**
 * 技术类别表
 *
 * @author David
 * @email 
 * @date 2020-01-30 10:58:45
 */
public interface ITechRequirementCategoryService extends IService<TechRequirementCategory> {

    
    boolean saves(TechRequirementCategory entity);
}

