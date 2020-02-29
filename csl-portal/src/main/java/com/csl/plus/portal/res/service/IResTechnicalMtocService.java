package com.csl.plus.portal.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResTechnicalMtoc;


/**
 * 需求表
 *
 * @author David
 * @email
 * @date 2020-02-29 11:32:27
 */
public interface IResTechnicalMtocService extends IService<ResTechnicalMtoc> {


    boolean saves(ResTechnicalMtoc entity);
}

