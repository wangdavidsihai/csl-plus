package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResTechnicalCategory;


/**
 * 技术类别表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
public interface IResTechnicalCategoryService extends IService<ResTechnicalCategory> {

    
    boolean saves(ResTechnicalCategory entity);
}

