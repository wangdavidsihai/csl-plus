package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResExpertData;


/**
 * 专家表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:48:23
 */
public interface IResExpertDataService extends IService<ResExpertData> {

    
    boolean saves(ResExpertData entity);
}

