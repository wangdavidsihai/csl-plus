package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResTechnicalCategoryMtoc;


/**
 * 技术类别表
 *
 * @author David
 * @email
 * @date 2020-02-29 11:32:27
 */
public interface IResTechnicalCategoryMtocService extends IService<ResTechnicalCategoryMtoc> {


    boolean saves(ResTechnicalCategoryMtoc entity);
}

