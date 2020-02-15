package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResExpert;


/**
 * 专家表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:48:23
 */
public interface IResExpertService extends IService<ResExpert> {

    
    boolean saves(ResExpert entity);
}

