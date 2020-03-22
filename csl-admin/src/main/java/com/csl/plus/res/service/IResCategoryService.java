package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResCategory;


/**
 * 资源类别表
 *
 * @author David
 * @email
 * @date 2020-03-20 21:16:19
 */
public interface IResCategoryService extends IService<ResCategory> {


    boolean saves(ResCategory entity);

    int updateShowStatus(Long id, Integer showStatus);
}

