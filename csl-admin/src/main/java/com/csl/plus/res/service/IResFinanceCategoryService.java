package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResFinanceCategory;


/**
 * 金融类别表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
public interface IResFinanceCategoryService extends IService<ResFinanceCategory> {

    
    boolean saves(ResFinanceCategory entity);
}

