package com.csl.plus.portal.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResProjCategory;


/**
 * 项目类别表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
public interface IResProjCategoryService extends IService<ResProjCategory> {

    
    boolean saves(ResProjCategory entity);
}

