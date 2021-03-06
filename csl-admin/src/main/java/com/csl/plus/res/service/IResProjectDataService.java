package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResProjectData;


/**
 * 项目表详细信息
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
public interface IResProjectDataService extends IService<ResProjectData> {


    boolean saves(ResProjectData entity);
}

