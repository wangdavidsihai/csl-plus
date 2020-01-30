package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.FinRequirementCategory;


/**
 * 金融类别表
 *
 * @author David
 * @email 
 * @date 2020-01-30 16:15:33
 */
public interface IFinRequirementCategoryService extends IService<FinRequirementCategory> {

    
    boolean saves(FinRequirementCategory entity);
}

