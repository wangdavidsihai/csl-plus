package com.csl.plus.portal.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResProductAppArea;


/**
 * 资源应用领域表
 *
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
public interface IResProductAppAreaService extends IService<ResProductAppArea> {

    
    boolean saves(ResProductAppArea entity);
}

