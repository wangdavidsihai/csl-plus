package com.csl.plus.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.rms.entity.RequirementCategory;


/**
 * 需求类别表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2020-01-04 20:03:44
 */
public interface IRequirementCategoryService extends IService<RequirementCategory> {

    
    boolean saves(RequirementCategory entity);
}

