package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResFinanceAppArea;


/**
 * 金融应用领域表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
public interface IResFinanceAppAreaService extends IService<ResFinanceAppArea> {

    
    boolean saves(ResFinanceAppArea entity);
}

