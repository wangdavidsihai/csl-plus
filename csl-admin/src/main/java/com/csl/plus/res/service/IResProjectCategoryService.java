package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResProjectCategory;


/**
 * 项目类别表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
public interface IResProjectCategoryService extends IService<ResProjectCategory> {


    boolean saves(ResProjectCategory entity);
}

